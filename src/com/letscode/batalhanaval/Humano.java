package com.letscode.batalhanaval;

import java.util.Scanner;

public class Humano {
    Tabuleiro tabuleiro;

    Humano() {
        this.tabuleiro = new Tabuleiro();
    }

    public void posicionarPecas() {
        for (int i = 0; i < this.tabuleiro.numeroDePecas; i++) {
            String posicao = receberPosicaoDoUsuario();

            if (!tabuleiro.verificarSePosicaoValida(posicao)) {
                i--;
                continue;
            }

            this.tabuleiro.posicoes[i] = posicao;
        }

        for (String pos : this.tabuleiro.posicoes) {
            System.out.println(pos);
        }
    }

    private String receberPosicaoDoUsuario() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toUpperCase().trim();
    }
}
