package br.com.jsadev.mercado;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import br.com.jsadev.mercado.common.BaseActivity;
import br.com.jsadev.mercado.common.DrawerMenu;
import br.com.jsadev.mercado.model.Categoria;
import br.com.jsadev.mercado.model.Lista;
import br.com.jsadev.mercado.model.Produto;
import br.com.jsadev.mercado.model.Unidade;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPositionClicked(DrawerMenu.HOME.getPosition());
        setupNavigation(savedInstanceState);

        criarDbCategorias();
        criarDbUnidades();
        criarDbProdutos();
        criarDbListas();
    }

    @Override
    public void reloadActivity() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void criarDbCategorias() {
        List<Categoria> categorias = Categoria.listAll(Categoria.class);
        if (categorias.size() == 0) {
            new Categoria(1, "Bazar e limpeza").save();
            new Categoria(2, "Bebidas").save();
            new Categoria(3, "Carnes").save();
            new Categoria(4, "Comidas prontas e congelados").save();
            new Categoria(5, "Frios, leites e derivados").save();
            new Categoria(6, "Frutas, ovos e verduras").save();
            new Categoria(7, "Higiene pessoal").save();
            new Categoria(8, "Importados").save();
            new Categoria(9, "Mercearias").save();
            new Categoria(10, "Padaria e sobremesa").save();
            new Categoria(11, "Saúde e beleza").save();
        }
    }

    private void criarDbUnidades() {
        List<Unidade> unidades = Unidade.listAll(Unidade.class);
        if (unidades.size() == 0) {
            new Unidade(1, "un").save();
            new Unidade(2, "dz").save();
            new Unidade(3, "mL").save();
            new Unidade(4, "L").save();
            new Unidade(5, "kg").save();
            new Unidade(6, "g").save();
            new Unidade(7, "Caixa").save();
            new Unidade(8, "Embalagem").save();
            new Unidade(9, "Galão").save();
            new Unidade(10, "Garrafa").save();
            new Unidade(11, "Lata").save();
            new Unidade(12, "Pacote").save();
        }
    }

    private void criarDbProdutos() {
        List<Produto> produtos = Produto.listAll(Produto.class);
        if (produtos.size() == 0) {
            new Produto(1, "Abacaxi", 5, 6.49D, 6, "http://universitariovestibulares.com.br/arquivos/temp/abacaxi.jpg").save();
            new Produto(2, "Alface", 5, 1.79D, 6, "http://universitariovestibulares.com.br/arquivos/temp/alface.jpg").save();
            new Produto(3, "Alho", 5, 22.9D, 6, "http://universitariovestibulares.com.br/arquivos/temp/alho.jpg").save();
            new Produto(4, "Banana", 5, 4.78D, 6, "http://universitariovestibulares.com.br/arquivos/temp/banana.jpg").save();
            new Produto(5, "Batata inglesa", 5, 4.98D, 6, "http://universitariovestibulares.com.br/arquivos/temp/batata.jpg").save();
            new Produto(6, "Caqui", 5, 2.99D, 6, "http://universitariovestibulares.com.br/arquivos/temp/caqui.jpg").save();
            new Produto(7, "Cenoura", 5, 2.49D, 6, "http://universitariovestibulares.com.br/arquivos/temp/cenoura.jpg").save();
        }
    }

    private void criarDbListas() {
        List<Lista> listas = Lista.listAll(Lista.class);
    }
}
