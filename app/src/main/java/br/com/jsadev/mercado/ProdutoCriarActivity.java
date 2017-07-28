package br.com.jsadev.mercado;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.jsadev.mercado.common.BaseActivity;
import br.com.jsadev.mercado.common.DrawerMenu;
import br.com.jsadev.mercado.common.Extras;
import br.com.jsadev.mercado.model.Categoria;
import br.com.jsadev.mercado.model.Produto;

public class ProdutoCriarActivity extends BaseActivity {

    private ImageView btnCancelar;
    private ImageView btnSalvar;
    private EditText txtNome;
    private Spinner ddlUnidade;
    private EditText txtPreco;
    private Spinner ddlCategoria;
    private ImageView imgFoto;

    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_criar);

        // cria drawer menu
        setPositionClicked(DrawerMenu.PRODUTOS.getPosition());
        setupNavigation(savedInstanceState);

        // views
        btnCancelar = (ImageView) findViewById(R.id.button_cancelar);
        btnSalvar = (ImageView) findViewById(R.id.button_salvar);
        txtNome = (EditText) findViewById(R.id.input_nome);
        ddlUnidade = (Spinner) findViewById(R.id.select_unidade);
        txtPreco = (EditText) findViewById(R.id.input_preco);
        ddlCategoria = (Spinner) findViewById(R.id.select_categoria);
        imgFoto = (ImageView) findViewById(R.id.image_foto);

        // spinners
        String[] unidades = {"un", "dz", "mL", "L", "kg", "g", "Caixa", "Embalagem", "Galão", "Garrafa", "Lata", "Pacote"};
        ArrayAdapter<String> unAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, unidades);
        ddlUnidade.setAdapter(unAdapter);

        String[] categorias = {"Bazar e Limpeza", "Bebidas", "Carnes", "Comidas prontas e congelados", "Frios, leites e derivados", "Frutas, ovos e verduras", "Higiene pessoal", "Importados", "Mercearia", "Padaria e sobremesa", "Saúde e beleza"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categorias);
        ddlCategoria.setAdapter(catAdapter);

        // listeners
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = txtNome.getEditableText().toString();
                if (nome.isEmpty()) {
                    final Snackbar snackbar = Snackbar.make(findViewById(R.id.root), "Nome não pode ser vazio.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    return;
                }

                finish();
            }
        });

        // preencher campos
        if (getIntent().getExtras() != null) {
            Produto produto = (Produto) getIntent().getExtras().get(Extras.PRODUTO.getCode());
            txtNome.setText(produto.getNome());
            txtPreco.setText(String.valueOf(produto.getPreco()));
            ddlCategoria.setSelection(0);

            ddlUnidade.setSelection(produto.getUnidadeId() - 1);
            ddlCategoria.setSelection(produto.getCategoriaId() - 1);

            Picasso.with(this).load(produto.getFoto()).into(imgFoto);
        }

        // camera
        if (ContextCompat.checkSelfPermission(ProdutoCriarActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 5);
        }
        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgFoto.setImageBitmap(photo);
        }
    }

    @Override
    public String actionBarName() {
        return "Novo produto";
    }

    @Override
    public boolean isChildView() {
        return true;
    }

    @Override
    public void reloadActivity() {
        finish();
        Intent intent = new Intent(this, ProdutoCriarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
