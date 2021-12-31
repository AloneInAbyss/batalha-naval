package com.letscode.batalhanaval;

public class Main {

    public static void main(String[] args) {
        int qtdPecas = 4;
        int qtdColunas = 10;
        char[] nomeLinhas = {'A','B','C','D','E','F','G','H','I','J'};

        Jogo jogo = new Jogo(qtdPecas, qtdColunas, nomeLinhas);
        jogo.inicializarJogo();

        while (!jogo.fimDeJogo) {
            jogo.rodada();
        }

    }
}
