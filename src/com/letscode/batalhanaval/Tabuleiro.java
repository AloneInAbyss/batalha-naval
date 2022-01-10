package com.letscode.batalhanaval;

import java.util.ArrayList;
import java.util.Objects;

public class Tabuleiro {
    public short numeroDePecas;
    public String[] posicoesDosNavios;
    public ArrayList<String> jogadasAnteriores, jogadasComAcerto;

    public Tabuleiro() {
        numeroDePecas = 2;
        posicoesDosNavios = new String[numeroDePecas];
        jogadasAnteriores = new ArrayList<>();
        jogadasComAcerto = new ArrayList<>();
    }

    public void mostrarTabuleiro() {
        String[][] tabuleiro = new String[10][10];

        System.out.println("----------------------- BATALHA NAVAL ---------------------------");
        System.out.println("-------------------------- JOGADOR ------------------------------");
        System.out.println("| - |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |");
        System.out.println("| A |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| B |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| C |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| D |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| E |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| F |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| G |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| H |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| I |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("| J |     |     |     |     |     |     |     |     |     |     |");
        System.out.println("----| Jogador: " + numeroDePecas + " |------- NAVIOS RESTANTES -------| CPU: " + numeroDePecas + " | ----");
    }

    private boolean verificarSePosicaoInvalida(String posicao) {
        if (posicao.length() != 2) return true;

        switch (posicao.charAt(0)) {
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
                break;
            default:
                return true;
        }

        switch (posicao.charAt(1)) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                break;
            default:
                return true;
        }

        return false;
    }

    public boolean verificarSePosicaoRepetida(String posicaoParaAdicionar) {
        if (verificarSePosicaoInvalida(posicaoParaAdicionar)) return true;

        for (String posicao : posicoesDosNavios) {
            if (posicao == null) return false;
            if (posicao.equals(posicaoParaAdicionar)) return true;
        }
        return false;
    }

    public boolean verificarSeJogadaInvalida(String posicaoDaJogada) {
        if (verificarSePosicaoInvalida(posicaoDaJogada)) return true;

        for (String jogada : jogadasAnteriores) {
            if (jogada == null) return false;
            if (jogada.equals(posicaoDaJogada)) return true;
        }
        return false;
    }

    public boolean checarSeTiroAcertou(String posicaoDoTiro) {
        for (String item : posicoesDosNavios) {
            if (Objects.equals(posicaoDoTiro, item)) {
                jogadasComAcerto.add(posicaoDoTiro);
                return true;
            }
        }
        return false;
    }
}
