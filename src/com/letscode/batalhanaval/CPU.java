package com.letscode.batalhanaval;

import java.util.concurrent.ThreadLocalRandom;

public class CPU {
    Tabuleiro tabuleiro;

    public CPU() {
        this.tabuleiro = new Tabuleiro();
    }

    public void posicionarPecas() {
        for (int i = 0; i < this.tabuleiro.numeroDePecas; i++) {
            String posicao = sortearPosicao();

            if (tabuleiro.verificarSePosicaoRepetida(posicao)) {
                System.out.println("Posição da CPU repetida!");
                i--;
                continue;
            }

            this.tabuleiro.posicoes[i] = posicao;
        }

        // Debug
        System.out.println("CPU:");
        for (String pos : this.tabuleiro.posicoes) {
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
}
