package br.com.jsadev.mercado.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by janailson on 26/07/2017.
 */

public class Lista extends SugarRecord<Lista> implements Serializable {

    private String nome;
    private String subtitulo;
    private boolean status;

    public Lista() {
    }

    public Lista(String nome, String subtitulo, boolean status) {
        this.nome = nome;
        this.subtitulo = subtitulo;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
