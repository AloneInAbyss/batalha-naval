package com.letscode.batalhanaval;

public class Jogo {
    Humano humano;
    CPU cpu;

    public Jogo() {
        this.humano = new Humano();
        this.cpu = new CPU();

        gameLoop();
    }

    private void gameLoop() {
        humano.posicionarPecas();
        cpu.posicionarPecas();
    }
}
