package projetoviruszumbi;

import java.util.ArrayList;

/**
 * Classe Mundo. Fornece métodos e atributos para o registro do mundo
 *
 * @author Jean-Luc Bonnet
 */
public class Mundo {

    /**
     * Mapa do mundo
     */
    public int[][] mapa;
    private ArrayList<PessoaSaudavel> pessoasSaudaveis = new ArrayList<>();
    private ArrayList<PessoaDoente> pessoasDoentes = new ArrayList<>();
    private ArrayList<Zumbi> zumbis = new ArrayList<>();
    private ArrayList<Hospital> hospitais = new ArrayList<>();
    private Virus virus = new Virus("Vírus zumbi");

    /**
     * Método construtor da classe Mundo
     */
    public Mundo() {
        // Cria o mapa com 15 linhas e 45 colunas
        // TODO mudar tamanho do mapa
        this.mapa = new int[5][10];
        reiniciaMapa();

        // TODO mudar valores iniciais
        // Cria as pessoas saudáveis iniciais
        int numInicialPS = 2;
        for (int i = 0; i < numInicialPS; i++) {
            int x = (int) (Math.random() * mapa.length);
            int y = (int) (Math.random() * mapa[0].length);
            int cor = Integer.parseInt(ICores.PESSOA_SAUDAVEL.replace("\u001B[", "").replace("m", ""));
            pessoasSaudaveis.add(new PessoaSaudavel(x, y, cor));
        }

        // TODO mudar valores iniciais
        // Cria as pessoas doentes iniciais
        int numInicialPD = 1;
        for (int i = 0; i < numInicialPD; i++) {
            int x = (int) (Math.random() * mapa.length);
            int y = (int) (Math.random() * mapa[0].length);
            int cor = Integer.parseInt(ICores.PESSOA_SAUDAVEL.replace("\u001B[", "").replace("m", ""));
            pessoasDoentes.add(new PessoaDoente(x, y, cor, virus));
        }
    }

    /**
     * Método getMapa. Retorna o mapa do mundo
     *
     * @return mapa do mundo
     */
    public int[][] getMapa() {
        return mapa;
    }

    /**
     * Método setMapa. Registra o mapa do mundo
     *
     * @param mapa mapa do mundo
     */
    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }

    /**
     * Método para atualizar o mundo
     */
    public void atualizaMundo() {
        reiniciaMapa();

        // Move todas as pessoas saudáveis
        for (PessoaSaudavel p : pessoasSaudaveis) {
            int x = p.getX();
            int y = p.getY();
            this.mapa[x][y] = 2;

            p.mover();
            if (p.getX() > mapa.length - 1) {
                p.setX(0);
            } else if (p.getX() < 0) {
                p.setX(mapa.length - 1);
            }

            if (p.getY() > mapa[0].length - 1) {
                p.setY(0);
            } else if (p.getY() < 0) {
                p.setY(mapa[0].length - 1);
            }
        }

        // Move todas as pessoas doentes
        for (PessoaDoente p : pessoasDoentes) {
            int x = p.getX();
            int y = p.getY();
            this.mapa[x][y] = 3;

            p.mover();
            if (p.getX() > mapa.length - 1) {
                p.setX(0);
            } else if (p.getX() < 0) {
                p.setX(mapa.length - 1);
            }

            if (p.getY() > mapa[0].length - 1) {
                p.setY(0);
            } else if (p.getY() < 0) {
                p.setY(mapa[0].length - 1);
            }
        }
    }

    /**
     * Método para desenhar o mundo
     */
    public void desenhaMundo() {
        // Legenda do mundo
        // Número de pessoas saudáveis
        System.out.print(ICores.PESSOA_SAUDAVEL + " " + ICores.RESET + " Saudáveis: " + pessoasSaudaveis.size());
        System.out.print("   ");
        // Número de pessoas doentes
        System.out.print(ICores.PESSOA_DOENTE + " " + ICores.RESET + " Doentes: " + pessoasDoentes.size());
        System.out.print("   ");
        // Número de zumbis
        System.out.print(ICores.ZUMBI + " " + ICores.RESET + " Zumbis: " + zumbis.size());
        System.out.print("\n\n");

        for (int i = 0; i < this.mapa.length; i++) {
            for (int j = 0; j < this.mapa[0].length; j++) {
                switch (mapa[i][j]) {
                    case 0:
                        System.out.print(ICores.VAZIO + " " + ICores.RESET);
                        break;
                    case 1:
                        System.out.print(ICores.BORDA + " " + ICores.RESET);
                        break;
                    case 2:
                        System.out.print(ICores.PESSOA_SAUDAVEL + " " + ICores.RESET);
                        break;
                    case 3:
                        System.out.print(ICores.PESSOA_DOENTE + " " + ICores.RESET);
                        break;
                    case 4:
                        System.out.print(ICores.ZUMBI + " " + ICores.RESET);
                        break;
                    case 5:
                        System.out.print(ICores.HOSPITAL_PAREDES + " " + ICores.RESET);
                        break;
                    case 6:
                        System.out.print(ICores.HOSPITAL_CRUZ + " " + ICores.RESET);
                        break;
                }
            }
            System.out.print("\n");
        }
        System.out.println("");
    }

    private void reiniciaMapa() {
        // reseta o mapa com "1" nas bordas e "0" nas demais posições 
        for (int i = 0; i < this.mapa.length; i++) {
            for (int j = 0; j < this.mapa[0].length; j++) {
                if (i == 0 || i == this.mapa.length - 1) {
                    mapa[i][j] = 1;
                } else {
                    if (j == 0 || j == this.mapa[0].length - 1) {
                        mapa[i][j] = 1;
                    } else {
                        mapa[i][j] = 0;
                    }
                }
            }
        }
    }
}
