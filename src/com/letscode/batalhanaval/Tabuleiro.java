package com.letscode.batalhanaval;

public class Tabuleiro {
    public short numeroDePecas = 10;
    public String[] posicoes = new String[numeroDePecas];

    public boolean verificarSePosicaoInvalida(String posicao) {
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

        if (verificarSePosicaoRepetida(posicao)) return true;

        return false;
    }

    public boolean verificarSePosicaoRepetida(String posicaoParaAdicionar) {
        for (String posicao : this.posicoes) {
            if (posicao == null) return false;
            if (posicao.equals(posicaoParaAdicionar)) return true;
        }
        return false;
    }
}
