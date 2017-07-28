package br.com.jsadev.mercado.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by janailson on 27/07/2017.
 */

public class Unidade extends SugarRecord<Unidade> implements Serializable {

    private int id;
    private String nome;

    public Unidade() {
    }

    public Unidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
