package com.letscode.batalhanaval;

import java.util.Scanner;

public class Humano {
    Tabuleiro tabuleiro;

    public Humano() {
        this.tabuleiro = new Tabuleiro();
    }

    public void posicionarPecas() {
        for (int i = 0; i < this.tabuleiro.numeroDePecas; i++) {
            String posicao = receberPosicaoDoUsuario();

            if (tabuleiro.verificarSePosicaoInvalida(posicao) || tabuleiro.verificarSePosicaoRepetida(posicao)) {
                System.out.println("Posição inválida!");
                i--;
                continue;
            }

            this.tabuleiro.posicoes[i] = posicao;
        }

        // Debug
        System.out.println("Player:");
        for (String pos : this.tabuleiro.posicoes) {
            System.out.println(pos);
        }
    }

    private String receberPosicaoDoUsuario() {
        System.out.println("Digite uma posição (por exemplo, A4): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toUpperCase().trim();
    }

    public void fazerJogada(CPU cpu) {
        String jogada = "";
        boolean jogadaInvalida = true;

        while (jogadaInvalida) {
            String posicao = receberPosicaoDoUsuario();

            if (tabuleiro.verificarSeJogadaInvalida(posicao)) {
                System.out.println("Posição inválida!");
                continue;
            }

            jogada = posicao;
            jogadaInvalida = false;
        }

        tabuleiro.jogadas.add(jogada);

        cpu.receberJogada(jogada);

        // Debug
        for (String item : tabuleiro.jogadas) {
            System.out.println(item);
        }
    }
}
