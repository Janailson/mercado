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

import br.com.jsadev.mercado.adapter.ProdutoAdapter;
import br.com.jsadev.mercado.common.BaseActivity;
import br.com.jsadev.mercado.common.DrawerMenu;
import br.com.jsadev.mercado.model.Produto;

public class ProdutoActivity extends BaseActivity {

    private RecyclerView produtosList;
    private FloatingActionButton addProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        // cria drawer menu
        setPositionClicked(DrawerMenu.PRODUTOS.getPosition());
        setupNavigation(savedInstanceState);

        // views
        produtosList = (RecyclerView) findViewById(R.id.recycle_view_produtos_list);
        addProduto = (FloatingActionButton) findViewById(R.id.btn_add_produto);

        // cria lista de 'categorias'
        montarListaqem();

        // listeners
        addProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdutoActivity.this, ProdutoCriarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public String actionBarName() {
        return DrawerMenu.PRODUTOS.getNome();
    }

    @Override
    public boolean isChildView() {
        return true;
    }

    @Override
    public void reloadActivity() {
        finish();
        Intent intent = new Intent(this, ProdutoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void montarListaqem() {
        List<Produto> produtos = Produto.listAll(Produto.class);

        ProdutoAdapter adapter = new ProdutoAdapter(this, produtos);
        produtosList.setAdapter(adapter);
        produtosList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        produtosList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
