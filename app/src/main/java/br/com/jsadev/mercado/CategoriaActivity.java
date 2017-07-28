package br.com.jsadev.mercado;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.com.jsadev.mercado.adapter.CategoriaAdapter;
import br.com.jsadev.mercado.common.BaseActivity;
import br.com.jsadev.mercado.common.DrawerMenu;
import br.com.jsadev.mercado.model.Categoria;

public class CategoriaActivity extends BaseActivity {

    private RecyclerView categoriasList;
    private FloatingActionButton addCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        // cria drawer menu
        setPositionClicked(DrawerMenu.CATEGORIAS.getPosition());
        setupNavigation(savedInstanceState);

        // views
        categoriasList = (RecyclerView) findViewById(R.id.recycle_view_categorias_list);
        addCategoria = (FloatingActionButton) findViewById(R.id.btn_add_categoria);

        // cria lista de 'categorias'
        montarListaqem();

        // listeners
        addCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriaActivity.this, CategoriaCriarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public String actionBarName() {
        return DrawerMenu.CATEGORIAS.getNome();
    }

    @Override
    public boolean isChildView() {
        return true;
    }

    @Override
    public void reloadActivity() {
        finish();
        Intent intent = new Intent(this, CategoriaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void montarListaqem() {
        List<Categoria> categorias = Categoria.listAll(Categoria.class);

        CategoriaAdapter adapter = new CategoriaAdapter(this, categorias);
        categoriasList.setAdapter(adapter);
        categoriasList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        categoriasList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

}
