package com.letscode.batalhanaval;

import java.util.*;

public class Tabuleiro {
    int qtdPecas, qtdColunas, countPecas;
    char[] nomeLinhas;
    Peca[] pecasHumano; // Lista de peças do jogador
    Peca[] pecasCPU; // Lista de peças do computador

    Scanner sc = new Scanner(System.in);
    String coordenada, editar;
    String[] oldCoor, coor;

    Random r = new Random();

    char[][] tabuleiro;
    Map<String, Integer> dicRows;

    protected Tabuleiro(int qtdPecas, int qtdColunas, char[] nomeLinhas){ //Inicializa as listas de peças com a qtd indicada
        this.qtdPecas = qtdPecas;
        this.qtdColunas = qtdColunas;
        this.nomeLinhas = nomeLinhas;
        this.pecasCPU = new Peca[qtdPecas];
        this.pecasHumano = new Peca[qtdPecas];
        this.tabuleiro = montarTabuleiro();
        this.dicRows = gerarDicionario(this.nomeLinhas);
    }

    protected HashMap<String, Integer> gerarDicionario(char[] nomeLinhas) {
        // Dicionário para relacionar o nome das linhas do tabuleiro com a posição no array tabuleiro [][]
        dicRows = new HashMap<String, Integer>() {{
            for (int i = 1; i< nomeLinhas.length + 1; i++) {
                put(String.valueOf(nomeLinhas[i-1]),i);
            }
        }};

        /*System.out.println("Elementos do dicionário");
        for (char key: nomeLinhas) {
            System.out.println(key + " --> " + dicRows.get(String.valueOf(key)));
        }*/

        return (HashMap<String, Integer>) dicRows;
    }

    protected char[][] montarTabuleiro(){

        char[][] tab = new char[this.nomeLinhas.length+1][5+2*(this.qtdColunas-1)];
        int count = 0;

        for (int j = 0; j < 5 + 2 * (this.qtdColunas - 1); j++) {
            if (j % 2 == 0) {
                tab[0][j] = '|';
            } else {
                if (j>2){
                    tab[0][j] = String.valueOf(j-3-count).charAt(0);
                    count++;
                }else{
                    tab[0][j] = ' ';
                }
            }
        }

        for (int i = 1; i<this.nomeLinhas.length+1; i++) {
            for (int j = 0; j < 5 + 2 * (this.qtdColunas - 1); j++) {
                if (j % 2 == 0) {
                    tab[i][j] = '|';
                } else {
                    tab[i][j] = ' ';
                }
            }
            tab[i][1] = this.nomeLinhas[i-1];
        }
        return tab;
    }

    protected void posicionarPecas() {
        /***
         * Função para posicionar as peças do jogador:
         * A função espera uma string contendo linha+coluna, ex: A2
         * No lugar da coordenada pode-se utilizar o comendo edit para modificar a posição de uma peça
        ***/

        for (int i = 0; i<this.pecasHumano.length; i++){
            System.out.println("Por favor, posicione a " + (i+1) + "ª peça: ");
            coordenada = sc.nextLine();
            coordenada = coordenada.toUpperCase();

            if (!coordenada.equals("EDIT")) { //posiciona uma peça
                coor = coordenada.split("");

                if (verificarCasaLivre(this.pecasHumano, coordenada)){
                    this.pecasHumano[i] = new Submarino(i,0, coor[0], Integer.valueOf(coor[1])); // inicializa a instância da peça
                    this.tabuleiro[dicRows.get(coor[0])][3+2*Integer.valueOf(coor[1])] = 'N'; // modifica a representação gráfica do tabuleiro
                    imprimirTabuleiro();
                } else {
                    i--;
                    System.out.println("Coordenada Inválida!!");
                }

            } else { // modifica a posição de uma peça
                editarPosicao();
                i--;
            }
        }

        System.out.println("Peças posicionadas. Deseja editar a posição de alguma peça [S/N]? ");
        editar = sc.nextLine();
        editar = editar.toUpperCase();

        if (editar.equals("S")){
            editarPosicao();
        }

        System.out.println("**** PEÇAS POSICIONADAS ****");
    }

    protected void posicionarPecasCPU() {
        countPecas = 0;
        while (countPecas < qtdPecas) {
            int rLinha = r.nextInt(this.nomeLinhas.length);
            int rColuna = r.nextInt(this.qtdColunas);

            coordenada = "" + nomeLinhas[rLinha] + rColuna;

            if (verificarCasaLivre(pecasCPU, coordenada)){
                coor = coordenada.split("");
                this.pecasCPU[countPecas] = new Submarino(countPecas,0, coor[0], Integer.valueOf(coor[1]));;
                countPecas++;
            }
        }

        //DELETAR
        System.out.println("PEÇAS CPU:");
        listaDePecas(pecasCPU);
    }

    protected boolean verificarCasaLivre(Peca[] listaDePecas, String coordenada){
        for (Peca peca: listaDePecas){
           if(peca != null && coordenada.equals(peca.getCoordenada())){
               return false;
           }
        }
        return true;
    }

    protected void editarPosicao(){

        countPecas = 0; //Qtd de peças já posicionadas
        for (Peca peca: this.pecasHumano) {
            if (peca != null) {
                countPecas++;
            }
        }

//        listaDePecas(this.pecasHumano);

        System.out.println("Selecione o id da peça para modificar sua posição: ");
        String idPeca = sc.nextLine();
        int id = Integer.valueOf(idPeca);

        if (id<countPecas){
            System.out.println("Digite a nova coordenada da peça: "); //CANCELAR
            String newCoor = sc.nextLine();
            newCoor = newCoor.toUpperCase();

            coor = newCoor.split(""); //nova coordenada da peça

            if (verificarCasaLivre(this.pecasHumano, newCoor)){
                oldCoor = this.pecasHumano[id].getCoordenada().split(""); //coordenada antiga da peça
                this.pecasHumano[id].changeCoordenada(coor[0], Integer.valueOf(coor[1])); // muda coordenada da peça

                this.tabuleiro[dicRows.get(oldCoor[0])][3+2*Integer.valueOf(oldCoor[1])] = ' '; // apaga peça do tabuleiro
                this.tabuleiro[dicRows.get(coor[0])][3+2*Integer.valueOf(coor[1])] = 'N'; // reposiciona peça no tabuleiro

                imprimirTabuleiro();
                //listaDePecas(this.pecasHumano);
            } else {
                System.out.println("Coordenada inválida!!");
            }
        }else{
            System.out.println("Id inválido!!");
        }

        System.out.println("Deseja editar a posição de outra peça [S/N]? ");
        editar = sc.nextLine();
        editar = editar.toUpperCase();

        if (editar.equals("S")){
            editarPosicao();
        }

    }

    protected void imprimirTabuleiro() {
        System.out.println("-----------------------");
        System.out.println("        JOGADOR");
        System.out.println("-----------------------");

        for (char[] row : this.tabuleiro) {
            for (char collumn : row) {
                System.out.print(collumn);
            }
            System.out.println();
            System.out.println("-----------------------"); //usar string.repeat() com Java 11
        }

    }

    protected void listaDePecas(Peca[] lista) {
        System.out.println("****** LISTA DE PEÇAS ******");
        for (Peca peca: lista){
            if (peca != null) {
                System.out.println(peca);
                System.out.println("****************************");
            }
        }
    }

    protected void finalizaJogo() {
        //implementar
    }

}
