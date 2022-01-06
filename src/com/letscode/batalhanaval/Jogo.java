package com.letscode.batalhanaval;

public class Jogo {
    Humano humano;
    CPU cpu;

    Jogo() {
        this.humano = new Humano();
        this.cpu = new CPU();

        gameLoop();
    }

    private void gameLoop() {
        humano.posicionarPecas();
    }
}
