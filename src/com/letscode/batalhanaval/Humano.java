package com.letscode.batalhanaval;

import java.util.Scanner;

public class Humano {
    Tabuleiro tabuleiro;
    public short pontos;

    public Humano() {
        tabuleiro = new Tabuleiro();
        pontos = tabuleiro.numeroDePecas;
    }

    public void posicionarPecas() {
        for (int i = 0; i < tabuleiro.numeroDePecas; i++) {
            String posicao = receberPosicaoDoUsuario();

            if (tabuleiro.verificarSePosicaoRepetida(posicao)) {
                System.out.println("Posição inválida!");
                i--;
                continue;
            }

            tabuleiro.posicoes[i] = posicao;
        }

        // Debug
        System.out.println("Player:");
        for (String pos : tabuleiro.posicoes) {
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

            jogadaInvalida = false;
            jogada = posicao;
        }

        tabuleiro.jogadas.add(jogada);

        cpu.receberJogada(jogada);

        // Debug
        for (String item : tabuleiro.jogadas) {
            System.out.println(item);
        }
    }

    public void receberJogada(String posicao) {
        if (tabuleiro.checarSeTiroAcertou(posicao)) pontos--;
        System.out.println("Seu Score: " + pontos);
    }
}
