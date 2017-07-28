package br.com.jsadev.mercado.common;

/**
 * Created by janailson on 28/07/2017.
 */

public enum Extras {

    CATEGORIA("CATEGORIA"),
    LISTA("LISTA"),
    PRODUTO("PRODUTO"),
    UNIDADE_MEDIDA("UNIDADE_MEDIDA");

    private String code;

    private Extras(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
