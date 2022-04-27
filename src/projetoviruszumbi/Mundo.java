package projetoviruszumbi;

import java.awt.color.ICC_ColorSpace;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    // Pessoas saudáveis
    private ArrayList<PessoaSaudavel> pessoasSaudaveis = new ArrayList<>();
    // Pessoas doentes
    private ArrayList<PessoaDoente> pessoasDoentes = new ArrayList<>();
    // zumbis
    private ArrayList<Zumbi> zumbis = new ArrayList<>();
    // hospitais
    private ArrayList<Hospital> hospitais = new ArrayList<>();
    // virus
    private Virus virus = new Virus("Vírus zumbi");

    // Vetor com as cores do mundo
    private String cores[] = {ICores.VAZIO, ICores.BORDA, ICores.PESSOA_SAUDAVEL, ICores.PESSOA_DOENTE, ICores.ZUMBI, ICores.HOSPITAL_PAREDES, ICores.HOSPITAL_CRUZ, ICores.RESET};

    private int chanceDeCura;

    /**
     * Método construtor da classe Mundo
     */
    public Mundo() {
        // Cria o mapa com 30 linhas e 90 colunas
        this.mapa = new int[30][90];
        reiniciaMapa();

        // Cria as pessoas saudáveis iniciais
        int numInicialPS = 100;
        for (int i = 0; i < numInicialPS; i++) {
            int x = (int) (Math.random() * mapa[0].length);
            int y = (int) (Math.random() * mapa.length);
            int cor = 0;
            try {
                cor = indexCor(cores, ICores.PESSOA_SAUDAVEL);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            pessoasSaudaveis.add(new PessoaSaudavel(x, y, cor));
        }

        // Cria as pessoas doentes iniciais
        int numInicialPD = 2;
        for (int i = 0; i < numInicialPD; i++) {
            int x = (int) (Math.random() * mapa[0].length);
            int y = (int) (Math.random() * mapa.length);
            int cor = 0;
            try {
                cor = indexCor(cores, ICores.PESSOA_DOENTE);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            pessoasDoentes.add(new PessoaDoente(x, y, cor, virus));
        }

        // Cria os hospitais
        int numHospitais = 3;
        int posicoes[][] = {{10, 5}, {10, mapa.length - 10}, {mapa[0].length - 15, mapa.length / 2 - 2}};
        for (int i = 0; i < numHospitais; i++) {
            hospitais.add(new Hospital(posicoes[i][0], posicoes[i][1], 5, 5, 5, 6));
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
        // Reinicia o mundo
        reiniciaMapa();

        // Adiciona os hospitais
        for (Hospital h : hospitais) {
            int x = h.getX();
            int y = h.getY();
            int l = h.getLargura();
            int a = h.getAltura();

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < a; j++) {
                    if ((i == (int) (l / 2) || i == (int) (l / 2) - 1 || i == (int) (l / 2) + 1) && j == (int) (a / 2)) {
                        mapa[y + i][x + j] = h.getCorCruz();
                    } else if ((j == (int) (a / 2) || j == (int) (a / 2) - 1 || j == (int) (a / 2) + 1) && i == (int) (l / 2)) {
                        mapa[y + i][x + j] = h.getCorCruz();
                    } else {
                        mapa[y + i][x + j] = h.getCorParede();
                    }

                }
            }
        }

        // Move todas as pessoas saudáveis
        for (PessoaSaudavel p : pessoasSaudaveis) {
            int x = p.getX();
            int y = p.getY();

            p.mover(mapa[0].length, mapa.length);

            this.mapa[y][x] = p.getCor();
        }

        ArrayList<PessoaDoente> psCuradas = new ArrayList<>();
        // Move todas as pessoas doentes
        for (PessoaDoente p : pessoasDoentes) {
            int x = p.getX();
            int y = p.getY();

            p.mover(mapa.length, mapa[0].length);

            this.mapa[y][x] = p.getCor();

            for (Hospital h : hospitais) {
                int xh = h.getX();
                int yh = h.getY();
                int lh = h.getLargura();
                int ah = h.getAltura();

                if (x >= xh && x <= lh - 1 + xh && y >= yh && y <= ah - 1 + yh) {
                    psCuradas.add(p);
                    int corPessoaSaudavel = 0;
                    try {
                        corPessoaSaudavel = indexCor(cores, ICores.PESSOA_SAUDAVEL);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    pessoasSaudaveis.add(new PessoaSaudavel(x, y, corPessoaSaudavel));
                }
            }
        }

        // Atualiza as pessoas doentes
        for (PessoaDoente c : psCuradas) {
            pessoasDoentes.remove(c);
        }

        ArrayList<Zumbi> zCurados = new ArrayList<>();
        // Move todos os zumbis
        for (Zumbi z : zumbis) {
            int x = z.getX();
            int y = z.getY();
            this.mapa[y][x] = z.getCor();

            z.mover(mapa.length, mapa[0].length);

            for (Hospital h : hospitais) {
                int xh = h.getX();
                int yh = h.getY();
                int lh = h.getLargura();
                int ah = h.getAltura();

                // Chance do zumbi ser curado detro do hospital
                Boolean curado = Math.random() * (100 - chanceDeCura) == 0;

                // Caso o zumbi entre no hosptal e a chance dele ser curado ocorrer, ele virará uma pessoa saudável
                if ((x >= xh && x <= lh - 1 + xh && y >= yh && y <= ah - 1 + yh) && curado) {
                    zCurados.add(z);
                    int corPessoaSaudavel = 0;
                    try {
                        corPessoaSaudavel = indexCor(cores, ICores.PESSOA_SAUDAVEL);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    pessoasSaudaveis.add(new PessoaSaudavel(x, y, corPessoaSaudavel));
                }
            }
        }

        // Atualiza os zumbis
        for (Zumbi z : zCurados) {
            zumbis.remove(z);
        }

        ArrayList<PessoaSaudavel> contaminados = new ArrayList<>();
        // Verifica se ocorreu um contaminação
        for (PessoaSaudavel ps : pessoasSaudaveis) {
            int x = ps.getX();
            int y = ps.getY();

            Boolean contaminado = false;
            for (PessoaDoente pd : pessoasDoentes) {
                int xpd = pd.getX();
                int ypd = pd.getY();

                // Verifica o distanciamento entre as pessoas, caso a distancia 
                // seja igual a 1 ou igual a 0, contaminado vale true
                Boolean ladoALado = distanciamento(1, x, y, xpd, ypd);
                Boolean mesmaPosicao = distanciamento(0, x, y, xpd, ypd);
                contaminado = ladoALado || mesmaPosicao;

                if (contaminado) {
                    break;
                }
            }

            if (!contaminado) {
                for (Zumbi z : zumbis) {
                    int xz = z.getX();
                    int yz = z.getY();

                    // Verifica o distanciamento entre as pessoas, caso a distancia 
                    // seja igual a 1 ou igual a 0, contaminado vale true
                    Boolean ladoALado = distanciamento(1, x, y, xz, yz);
                    Boolean mesmaPosicao = distanciamento(0, x, y, xz, yz);
                    contaminado = ladoALado || mesmaPosicao;

                    if (contaminado) {
                        break;
                    }
                }
            }
            
            // Se a cura chegar a 100% nenhuma pessoa será contaminada
            if (contaminado && chanceDeCura != 100) {
                // Transforma a pessoa saudável em pessoa doente
                int corPessoaDoente = 0;
                try {
                    corPessoaDoente = indexCor(cores, ICores.PESSOA_DOENTE);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                pessoasDoentes.add(new PessoaDoente(x, y, corPessoaDoente, this.virus));
                contaminados.add(ps);
            }
        }

        // Atualiza as pessoas saudáveis
        for (PessoaSaudavel c : contaminados) {
            pessoasSaudaveis.remove(c);
        }

        // Retorna o tempo atual em ms
        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();
        long dataAtual = data.getTime();

        ArrayList<PessoaDoente> novosZumbis = new ArrayList<>();
        // Verifica o tempo de contaminação de cada pessoa doente
        for (PessoaDoente pd : pessoasDoentes) {
            long tempo = dataAtual - pd.getDataDeContagio();
            int corZumbi = 0;
            try {
                corZumbi = indexCor(cores, ICores.ZUMBI);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            // Verifica se o tempo de contagio da pessoa doente é maior ou igual a 15000ms
            if (tempo >= 15000) {
                zumbis.add(new Zumbi(pd.getX(), pd.getY(), corZumbi, virus));
                novosZumbis.add(pd);
            }
        }

        // Atualiza as pessoas doentes
        for (PessoaDoente nz : novosZumbis) {
            pessoasDoentes.remove(nz);
        }

        // Solução: quanto maior o número de zumbis, melhor desenvolvida será a cura
        // Atualiza a chance de cura
        int numPessoas = pessoasDoentes.size() + pessoasSaudaveis.size() + zumbis.size();
        double razao = (float) zumbis.size() / numPessoas;
        int novaChance = (int) (razao * 100);
        // Garante que a cura só irá aumentar
        this.chanceDeCura = chanceDeCura > novaChance ? chanceDeCura : novaChance;
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
        System.out.print(ICores.ZUMBI + " " + ICores.RESET + " Zumbis: " + zumbis.size() + "\n");
        System.out.println("Cura: " + chanceDeCura + "%");

        for (int i = 0; i < this.mapa.length; i++) {
            for (int j = 0; j < this.mapa[0].length; j++) {
                System.out.print(cores[mapa[i][j]] + " " + cores[7]);
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
                    // borda
                    mapa[i][j] = 1;
                } else {
                    if (j == 0 || j == this.mapa[0].length - 1) {
                        // borda
                        mapa[i][j] = 1;
                    } else {
                        // meio
                        mapa[i][j] = 0;
                    }
                }
            }
        }
    }

    // Retorna o index da cor procurada
    private int indexCor(String vetor[], String cor) throws Exception {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].equals(cor)) {
                return i;
            }
        }
        throw new Exception("Cor não encontrada");
    }

    /**
     * Método distanciamento. Retorna true se ocorrer o distanciamento exato e
     * false caso o contrário
     *
     * @param distancia distancia a ser verificada
     * @param xa x da primeira pessoa
     * @param ya y da primeira pessoa
     * @param xb x da segunda pessoa
     * @param yb y da segunda pessoa
     * @return se existe o distanciamento desejado entre as duas pessoas
     */
    private Boolean distanciamento(int distancia, int xa, int ya, int xb, int yb) {
        // Distancia: 1
        // Posições: 
        //  | |    
        //  |█|  
        //  |█| 
        if (xa == xb && ya == yb + distancia) {
            return true;
        } // Posições: 
        //  |█|  
        //  |█| 
        //  | |
        else if (xa == xb && ya == yb - distancia) {
            return true;
        } // Posições: 
        //  | |
        //  |█|█ 
        //  | |
        else if (xa == xb + distancia && ya == yb) {
            return true;
        } // Posições: 
        //  | |
        // █|█| 
        //  | |
        else if (xa == xb - distancia && ya == yb) {
            return true;
        }

        // distancias diferentes da desejada
        return false;
    }

}
