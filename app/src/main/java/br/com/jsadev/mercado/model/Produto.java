package br.com.jsadev.mercado.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by janailson on 27/07/2017.
 */

public class Produto extends SugarRecord<Produto> implements Serializable {

    private int id;
    private String nome;
    private int unidadeId;
    private Double preco;
    private int categoriaId;
    private String foto;

    public Produto() {
    }

    public Produto(int id, String nome, int unidadeId, Double preco, int categoriaId, String foto) {
        this.id = id;
        this.nome = nome;
        this.unidadeId = unidadeId;
        this.preco = preco;
        this.categoriaId = categoriaId;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(int unidadeId) {
        this.unidadeId = unidadeId;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
