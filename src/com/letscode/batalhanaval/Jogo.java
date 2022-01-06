package com.letscode.batalhanaval;

public class Jogo {
    private final Humano humano;
    private final CPU cpu;
    boolean fimDeJogo;

    public Jogo() {
        humano = new Humano();
        cpu = new CPU();

        gameLoop();
    }

    private void gameLoop() {
        humano.posicionarPecas();
        cpu.posicionarPecas();

        while (!fimDeJogo) {
            humano.fazerJogada(cpu);
            cpu.fazerJogada(humano);

            if (checarSePontosAcabaram()) fimDeJogo = true;
        }
    }

    private boolean checarSePontosAcabaram() {
        if (humano.pontos <= 0) {
            return true;
        }

        if (cpu.pontos <= 0) {
            return true;
        }

        return false;
    }
}
