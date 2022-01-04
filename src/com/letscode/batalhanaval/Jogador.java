package com.letscode.batalhanaval;

public abstract class Jogador {
    private boolean venceu = false;
    private int score = 0;
    private boolean vez = false;

    protected Jogador() {
        this.venceu = false;
        this.score = 0;
        this.vez = false;
    }

//    protected void incrementarScore() {
//        this.score ++;
//    }
//
//    protected void atirar(String Lance) {
//        // Implementar
//    }

    protected void changeVez() {
        if (vez) {
            this.vez = false;
        }else {
            this.vez = true;
        }
    }

}
