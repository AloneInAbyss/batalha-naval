package com.letscode.batalhanaval;

public class Jogo {
    boolean fimDeJogo;
    int qtdPecas, qtdColunas;
    char[] nomeLinhas;

    protected Jogo(int qtdPecas, int qtdColunas, char[] nomeLinhas) {
        this.fimDeJogo = false;
        this.qtdPecas = qtdPecas;
        this.qtdColunas = qtdColunas;
        this.nomeLinhas = nomeLinhas;
    }

    protected void inicializarJogo() {
        Tabuleiro tab = new Tabuleiro(this.qtdPecas, this.qtdColunas, this.nomeLinhas);
//        CPU cpu = new CPU();
        Humano humano = new Humano();

        System.out.println("Inicio do jogo");
        tab.imprimirTabuleiro(); //tabuleiro vazio

        tab.posicionarPecas();
        tab.posicionarPecasCPU();

        humano.changeVez();
    }

//    protected void rodada() {
//        //if (player.vez)
//    }

//    protected boolean checarTiro() {
//        //implementar
//        return false;
//    }

//    protected void changeFimDeJogo() {
//        this.fimDeJogo = true;
//    }
//
//    protected boolean getFimDeJogo() {
//        return this.fimDeJogo;
//    }
}
