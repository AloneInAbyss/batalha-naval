package com.letscode.batalhanaval;

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
        humano.posicionarPecas();
        cpu.posicionarPecas();

        while (!fimDeJogo) {
            humano.fazerJogada(cpu);
            cpu.fazerJogada(humano);

            String resultado = verificarVencedor();
            if (!resultado.equals("")) {
                System.out.println("Vencedor: " + resultado);
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
