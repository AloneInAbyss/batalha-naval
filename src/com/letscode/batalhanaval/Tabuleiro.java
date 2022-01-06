package com.letscode.batalhanaval;

import java.util.ArrayList;
import java.util.Objects;

public class Tabuleiro {
    public short numeroDePecas;
    public String[] posicoes;
    public ArrayList<String> jogadas;

    public Tabuleiro() {
        numeroDePecas = 2;
        posicoes = new String[numeroDePecas];
        jogadas = new ArrayList<>();
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

        for (String posicao : posicoes) {
            if (posicao == null) return false;
            if (posicao.equals(posicaoParaAdicionar)) return true;
        }
        return false;
    }

    public boolean verificarSeJogadaInvalida(String posicaoDaJogada) {
        if (verificarSePosicaoInvalida(posicaoDaJogada)) return true;

        for (String jogada : jogadas) {
            if (jogada == null) return false;
            if (jogada.equals(posicaoDaJogada)) return true;
        }
        return false;
    }

    public boolean checarSeTiroAcertou(String posicaoDoTiro) {
        for (String item : posicoes) {
            if (Objects.equals(posicaoDoTiro, item)) return true;
        }
        return false;
    }
}
