package com.letscode.batalhanaval;

import java.util.Arrays;

public class Jogo {
    private final Humano humano;
    private final Cpu cpu;
    boolean fimDeJogo;

    public Jogo() {
        humano = new Humano();
        cpu = new Cpu();

        gameLoop();
    }

    private void gameLoop() {
        String resultado = "";

        humano.tabuleiro.mostrarTabuleiro("JOGADOR");
        humano.posicionarPecas();
        cpu.posicionarPecas();


        while (!fimDeJogo) {
            System.out.println("Faça sua jogada:");
            humano.fazerJogada(cpu);
            resultado = verificarVencedor();

            if (resultado.equals("")) {
                cpu.fazerJogada(humano);
                resultado = verificarVencedor();
            }

            if (!resultado.equals("")) {
                System.out.println("Vencedor: " + resultado);
                cpu.tabuleiro.mostrarTabuleiro("  CPU  ");

                System.out.println("Suas jogadas:");
                System.out.println(Arrays.toString(humano.tabuleiro.jogadasAnteriores.toArray()));

                System.out.println("Jogadas da CPU:");
                System.out.println(Arrays.toString(cpu.tabuleiro.jogadasAnteriores.toArray()));

                fimDeJogo = true;
            }
        }
    }

    private String verificarVencedor() {
        if (humano.pontos <= 0) {
            return "CPU";
        }

        if (cpu.pontos <= 0) {
            return "Player";
        }

        return "";
    }
}
