package br.com.jsadev.mercado;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import br.com.jsadev.mercado.common.BaseActivity;
import br.com.jsadev.mercado.common.DrawerMenu;
import br.com.jsadev.mercado.common.Extras;
import br.com.jsadev.mercado.model.Categoria;

public class CategoriaCriarActivity extends BaseActivity {

    private ImageView btnCancelar;
    private ImageView btnSalvar;
    private EditText txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_criar);

        // cria drawer menu
        setPositionClicked(DrawerMenu.CATEGORIAS.getPosition());
        setupNavigation(savedInstanceState);

        // views
        btnCancelar = (ImageView) findViewById(R.id.button_cancelar);
        btnSalvar = (ImageView) findViewById(R.id.button_salvar);
        txtNome = (EditText) findViewById(R.id.input_nome);

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

                Categoria categoria = (Categoria) getIntent().getExtras().get(Extras.CATEGORIA.getCode());
                categoria.setNome(nome);
                categoria.save();

                finish();
            }
        });

        // preencher campos
        if (getIntent().getExtras() != null) {
            Categoria categoria = (Categoria) getIntent().getExtras().get(Extras.CATEGORIA.getCode());
            txtNome.setText(categoria.getNome());
        }
    }

    @Override
    public String actionBarName() {
        if (getIntent().getExtras() == null)
            return "Nova categoria";
        else
            return "Alterar categoria";
    }

    @Override
    public boolean isChildView() {
        return true;
    }

    @Override
    public void reloadActivity() {
        finish();
        Intent intent = new Intent(this, CategoriaCriarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
