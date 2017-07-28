package br.com.jsadev.mercado.common;

/**
 * Created by janailson on 26/07/2017.
 */

public enum DrawerMenu {

    HOME("Home", 0),
    LISTAS("Listas", 2),
    PRODUTOS("Produtos", 3),
    CATEGORIAS("Categorias", 4),
    UNIDADES("Unidades de medida", 5);

    private String nome;
    private int position;

    private DrawerMenu(String nome, int position) {
        this.nome = nome;
        this.position = position;
    }

    public String getNome() {
        return nome;
    }

    public int getPosition() {
        return position;
    }

    public boolean isPosition(int position) {
        return (this.position == position);
    }

}
