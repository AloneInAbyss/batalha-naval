package com.letscode.batalhanaval;

import java.util.Arrays;
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
            tabuleiro.AdicionarSimboloAoTabuleiro(posicao,'N');
            tabuleiro.mostrarTabuleiro("JOGADOR");
        }

        System.out.println("Seus navios:");
        System.out.println(Arrays.toString(tabuleiro.posicoesDosNavios));
    }

    public void fazerJogada(Cpu cpu) {
        String jogada = "";
        boolean jogadaInvalida = true;
        boolean acerto;
        char simbolo;

        while (jogadaInvalida) {
            System.out.println();
            System.out.println("Faça sua jogada:");
            String posicao = scanner.nextLine().toUpperCase().trim();

            if (tabuleiro.verificarSeJogadaInvalida(posicao)) {
                System.out.println("Posição inválida!");
                System.out.println("Faça sua jogada:");
                continue;
            }

            jogadaInvalida = false;
            jogada = posicao;
        }

        tabuleiro.jogadasAnteriores.add(jogada);

        acerto = cpu.receberJogada(jogada);

        if (acerto) {
            if (tabuleiro.verificarSePosicaoRepetida(jogada)) {
                simbolo = 'X';
            } else {
                simbolo = '*';
            }
        } else {
            if (tabuleiro.verificarSePosicaoRepetida(jogada)) {
                simbolo = 'n';
            } else {
                simbolo = '-';
            }
        }

        tabuleiro.AdicionarSimboloAoTabuleiro(jogada, simbolo);
        tabuleiro.mostrarTabuleiro("JOGADOR");
    }

    public Boolean receberJogada(String posicao) {
        if (tabuleiro.checarSeTiroAcertou(posicao)) {
            pontos--;
            return true;
        }
        return false;
    }
}
