package br.com.jsadev.mercado;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.jsadev.mercado.adapter.ListaAdapter;
import br.com.jsadev.mercado.common.BaseActivity;
import br.com.jsadev.mercado.common.DrawerMenu;
import br.com.jsadev.mercado.model.Lista;

public class ListaActivity extends BaseActivity {

    private RecyclerView listasList;
    private FloatingActionButton addLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // cria drawer menu
        setPositionClicked(DrawerMenu.LISTAS.getPosition());
        setupNavigation(savedInstanceState);

        // views
        listasList = (RecyclerView) findViewById(R.id.recycle_view_listas_list);
        addLista = (FloatingActionButton) findViewById(R.id.btn_add_lista);

        // cria lista de 'listas'
        montarLista();

        // listeners
        addLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, ListaCriarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void montarLista() {
        List<Lista> listas = Lista.listAll(Lista.class);

        ListaAdapter adapter = new ListaAdapter(this, listas);
        listasList.setAdapter(adapter);
        listasList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listasList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        montarLista();
    }

    @Override
    public String actionBarName() {
        return DrawerMenu.LISTAS.getNome();
    }

    @Override
    public boolean isChildView() {
        return true;
    }

    @Override
    public void reloadActivity() {
        finish();
        Intent intent = new Intent(this, ListaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
