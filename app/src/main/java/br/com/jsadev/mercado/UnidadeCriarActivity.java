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
import br.com.jsadev.mercado.model.Unidade;

public class UnidadeCriarActivity extends BaseActivity {

    private ImageView btnCancelar;
    private ImageView btnSalvar;
    private EditText txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidade_criar);

        // cria drawer menu
        setPositionClicked(DrawerMenu.UNIDADES.getPosition());
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

                finish();
            }
        });

        // preencher campos
        if (getIntent().getExtras() != null) {
            Unidade unidade = (Unidade) getIntent().getExtras().get(Extras.UNIDADE_MEDIDA.getCode());
            txtNome.setText(unidade.getNome());
        }
    }

    @Override
    public String actionBarName() {
        return "Nova unidade";
    }

    @Override
    public boolean isChildView() {
        return true;
    }

    @Override
    public void reloadActivity() {
        finish();
        Intent intent = new Intent(this, UnidadeCriarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
