package br.com.jsadev.mercado;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.jsadev.mercado.adapter.CategoriaAdapter;
import br.com.jsadev.mercado.adapter.UnidadeAdapter;
import br.com.jsadev.mercado.common.BaseActivity;
import br.com.jsadev.mercado.common.DrawerMenu;
import br.com.jsadev.mercado.model.Categoria;
import br.com.jsadev.mercado.model.Unidade;

public class UnidadeActivity extends BaseActivity {

    private RecyclerView unidadesList;
    private FloatingActionButton addUnidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidade);

        // cria drawer menu
        setPositionClicked(DrawerMenu.UNIDADES.getPosition());
        setupNavigation(savedInstanceState);

        // views
        unidadesList = (RecyclerView) findViewById(R.id.recycle_view_unidades_list);
        addUnidade = (FloatingActionButton) findViewById(R.id.btn_add_unidade);

        // cria lista de 'categorias'
        montarListaqem();

        // listeners
        addUnidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnidadeActivity.this, UnidadeCriarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public String actionBarName() {
        return DrawerMenu.UNIDADES.getNome();
    }

    @Override
    public boolean isChildView() {
        return true;
    }

    @Override
    public void reloadActivity() {
        finish();
        Intent intent = new Intent(this, UnidadeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void montarListaqem() {
        List<Unidade> unidades = Unidade.listAll(Unidade.class);

        UnidadeAdapter adapter = new UnidadeAdapter(this, unidades);
        unidadesList.setAdapter(adapter);
        unidadesList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        unidadesList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
