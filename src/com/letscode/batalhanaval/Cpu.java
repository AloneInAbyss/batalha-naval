package com.letscode.batalhanaval;

import java.util.concurrent.ThreadLocalRandom;

public class Cpu {
    Tabuleiro tabuleiro;
    public short pontos;

    public Cpu() {
        tabuleiro = new Tabuleiro();
        pontos = tabuleiro.numeroDePecas;
    }

    public void posicionarPecas() {
        for (int i = 0; i < tabuleiro.numeroDePecas; i++) {
            String posicao = sortearPosicao();

            if (tabuleiro.verificarSePosicaoRepetida(posicao)) {
                i--;
                continue;
            }

            tabuleiro.posicoesDosNavios[i] = posicao;
            tabuleiro.AdicionarSimboloAoTabuleiro(posicao,'N');
        }

        // Debug
        System.out.println("[APAGAR] Navios da CPU:");
        for (String pos : tabuleiro.posicoesDosNavios) {
            System.out.println(pos);
        }
    }

    public String sortearPosicao() {
        int randomRow = ThreadLocalRandom.current().nextInt(0, 9 + 1);
        int randomColumn = ThreadLocalRandom.current().nextInt(0, 9 + 1);
        String position;

        switch (randomRow) {
            case 0:
                position = "A";
                break;
            case 1:
                position = "B";
                break;
            case 2:
                position = "C";
                break;
            case 3:
                position = "D";
                break;
            case 4:
                position = "E";
                break;
            case 5:
                position = "F";
                break;
            case 6:
                position = "G";
                break;
            case 7:
                position = "H";
                break;
            case 8:
                position = "I";
                break;
            case 9:
                position = "J";
                break;
            default:
                throw new Error("Falha no sorteio");
        }

        position += Integer.toString(randomColumn);

        return position;
    }

    public Boolean receberJogada(String posicao) {
        if (tabuleiro.checarSeTiroAcertou(posicao)) {
            pontos--;
            System.out.println("Parabéns, você acertou um navio!!");
            System.out.println("Navios restantes da CPU: " + pontos);
            return true;
        }
        System.out.println("Ops, tiro na água!");
        System.out.println("Navios restantes da CPU: " + pontos);
        return false;
    }

    public void fazerJogada(Humano humano) {
        String jogada = "";
        boolean jogadaInvalida = true;
        boolean acerto;
        char simbolo;

        while (jogadaInvalida) {
            jogada = sortearPosicao();

            if (tabuleiro.verificarSeJogadaInvalida(jogada)) {
                continue;
            }

            jogadaInvalida = false;
        }

        tabuleiro.jogadasAnteriores.add(jogada);

        acerto = humano.receberJogada(jogada);

        if (acerto) {
            if(tabuleiro.verificarSePosicaoRepetida(jogada)) {
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

        tabuleiro.AdicionarSimboloAoTabuleiro(jogada,simbolo);

        System.out.print("Jogada da CPU:");
        System.out.println(jogada);
        if (acerto) {
            System.out.println("Homens ao mar, você perdeu um navio!");
        } else {
            System.out.println("Sua frota não foi atingida. Tiro na água!");
        }

    }
}
