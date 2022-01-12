package com.letscode.batalhanaval;

import java.util.ArrayList;
import java.util.Objects;

public class Tabuleiro {
    public short numeroDePecas;
    public String[] posicoesDosNavios;
    public ArrayList<String> jogadasAnteriores, jogadasComAcerto;
    private final char[][] tab;

    public Tabuleiro() {
        numeroDePecas = 2;
        posicoesDosNavios = new String[numeroDePecas];
        jogadasAnteriores = new ArrayList<>();
        jogadasComAcerto = new ArrayList<>();
        tab = montarTabuleiro();
    }

    public char[][] montarTabuleiro(){
        char[][] tab = new char[11][67];
        char[] nomeLinhas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int count = 0;

        for (int j = 0; j < 67; j++) {
            if (j % 6 == 0) {
                tab[0][j] = '|';
            } else {
                if ((j-3) % 6 == 0 && j != 3){
                    tab[0][j] = String.valueOf(count).charAt(0);
                    count++;
                } else {
                    tab[0][j] = ' ';
                }
            }
        }

        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 67; j++) {
                if (j % 6 == 0) {
                    tab[i][j] = '|';
                } else {
                    tab[i][j] = ' ';
                }
            }
            tab[i][3] = nomeLinhas[i-1];
        }

        return tab;
    }

    public void mostrarTabuleiro(String nome) {
        System.out.println("------------------------ BATALHA NAVAL ----------------------------");
        System.out.println("--------------------------- " + nome + " -------------------------------");
        for (char[] row : tab) {
            for (char collumn : row) {
                System.out.print(collumn);
            }
            System.out.println();
            System.out.println("-------------------------------------------------------------------");
        }
    }

    public void AdicionarSimboloAoTabuleiro(String jogada, char simbolo) {
        int linha;
        int coluna;

        switch (jogada.charAt(0)) {
            case 'A':
                linha = 0;
                break;
            case 'B':
                linha = 1;
                break;
            case 'C':
                linha = 2;
                break;
            case 'D':
                linha = 3;
                break;
            case 'E':
                linha = 4;
                break;
            case 'F':
                linha = 5;
                break;
            case 'G':
                linha = 6;
                break;
            case 'H':
                linha = 7;
                break;
            case 'I':
                linha = 8;
                break;
            case 'J':
                linha = 9;
                break;
            default:
                throw new Error("Erro ao converter posição na matriz");
        }

        coluna = jogada.charAt(1) - '0'; // Converte char para int
        coluna = 9 + coluna * 6;

        tab[linha + 1][coluna] = simbolo;
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
