package com.letscode.batalhanaval;

import java.util.HashMap;
import java.util.Map;

public abstract class Peca {
    private int id;
    private int tipo;
    private String linha;
    private int coluna;
    private boolean afundado = false;

    Map<Integer, String> dicPecas = new HashMap<Integer, String>() {{
        put(0,"Submarino");
    }};

    protected Peca(int id, int tipo, String linha, int coluna) {
        this.id = id;
        this.tipo = tipo; // 0 = submarino; 1 = ...
        this.linha = linha;
        this.coluna = coluna;
    }

    protected void afundar() {
        this.afundado = true;
    }

    protected void changeCoordenada(String linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    protected String getCoordenada() {
        return this.linha + this.coluna;
    }

    public String toString() {
        return "ID: " + this.id + "\n" + "Tipo: " + dicPecas.get(this.tipo) + "\n" + "Coordenada: " + this.linha + this.coluna + "\n" + "Afundada: " + afundado;
    }

}
