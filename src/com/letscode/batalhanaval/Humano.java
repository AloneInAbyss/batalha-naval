package com.letscode.batalhanaval;

import java.util.Scanner;

public class Humano {
    Scanner scanner = new Scanner(System.in);
    Tabuleiro tabuleiro;
    public short pontos;

    public Humano() {
        tabuleiro = new Tabuleiro();
        pontos = tabuleiro.numeroDePecas;
    }

    public void posicionarPecas() {
        for (int i = 0; i < tabuleiro.numeroDePecas; i++) {
            System.out.println("Digite a " + (i+1) + "ª peça (por exemplo, A4): ");
            String posicao = scanner.nextLine().toUpperCase().trim();

            if (tabuleiro.verificarSePosicaoRepetida(posicao)) {
                System.out.println("Posição inválida!");
                i--;
                continue;
            }

            tabuleiro.posicoesDosNavios[i] = posicao;
        }

        // Debug
        System.out.println("Seus navios:");
        for (String pos : tabuleiro.posicoesDosNavios) {
            System.out.println(pos);
        }
    }

    public void fazerJogada(Cpu cpu) {
        String jogada = "";
        boolean jogadaInvalida = true;

        while (jogadaInvalida) {
            String posicao = scanner.nextLine().toUpperCase().trim();

            if (tabuleiro.verificarSeJogadaInvalida(posicao)) {
                System.out.println("Posição inválida!");
                continue;
            }

            jogadaInvalida = false;
            jogada = posicao;
        }

        tabuleiro.jogadasAnteriores.add(jogada);

        cpu.receberJogada(jogada);

        // Debug
        System.out.println("Suas jogadas:");
        for (String item : tabuleiro.jogadasAnteriores) {
            System.out.println(item);
        }
    }

    public void receberJogada(String posicao) {
        if (tabuleiro.checarSeTiroAcertou(posicao)) pontos--;
        System.out.println("Seus navios restantes: " + pontos);
    }
}
