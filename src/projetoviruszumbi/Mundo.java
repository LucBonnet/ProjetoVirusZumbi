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

    // Mapa do mundo
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

    private String cores[] = {ICores.VAZIO, ICores.BORDA, ICores.PESSOA_SAUDAVEL, ICores.PESSOA_DOENTE, ICores.ZUMBI, ICores.HOSPITAL_PAREDES, ICores.HOSPITAL_CRUZ, ICores.RESET};

    /**
     * Método construtor da classe Mundo
     */
    public Mundo() {
        // Cria o mapa com 30 linhas e 90 colunas
        this.mapa = new int[30][90];
        reiniciaMapa();

        // Cria as pessoas saudáveis iniciais
        int numInicialPS = 1;
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
        int numInicialPD = 10;
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
            this.mapa[y][x] = p.getCor();

            p.mover(mapa[0].length, mapa.length);
            
        }

        ArrayList<PessoaDoente> curados = new ArrayList<>();
        // Move todas as pessoas doentes
        for (PessoaDoente p : pessoasDoentes) {
            int x = p.getX();
            int y = p.getY();
            this.mapa[y][x] = p.getCor();

            p.mover(mapa.length, mapa[0].length);

            for (Hospital h : hospitais) {
                int xh = h.getX();
                int yh = h.getY();
                int lh = h.getLargura();
                int ah = h.getAltura();

                if (x >= xh && x <= lh + xh && y >= yh && y <= ah + yh) {
                    curados.add(p);
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
        
        for(PessoaDoente c : curados) {
            pessoasDoentes.remove(c);
        }

        // Move todos os zumbis
        for (Zumbi z : zumbis) {
            int x = z.getX();
            int y = z.getY();
            this.mapa[y][x] = z.getCor();

            z.mover(mapa.length, mapa[0].length);
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

                if (x == xpd && y == ypd) {
                    // mesma posição
                    contaminado = true;
                    break;
                } else if (x == xpd && y == ypd + 1) {
                    contaminado = true;
                    break;
                } else if (x == xpd && y == ypd - 1) {
                    contaminado = true;
                    break;
                } else if (x == xpd + 1 && y == ypd) {
                    contaminado = true;
                    break;
                } else if (x == xpd - 1 && y == ypd) {
                    contaminado = true;
                    break;
                }
            }

            for (Zumbi z : zumbis) {
                int xz = z.getX();
                int yz = z.getY();

                if (x == xz && y == yz) {
                    // mesma posição
                    contaminado = true;
                    break;
                } else if (x == xz && y == yz + 1) {
                    contaminado = true;
                    break;
                } else if (x == xz && y == yz - 1) {
                    contaminado = true;
                    break;
                } else if (x == xz + 1 && y == yz) {
                    contaminado = true;
                    break;
                } else if (x == xz - 1 && y == yz) {
                    contaminado = true;
                    break;
                }
            }

            if (contaminado) {
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

        for (PessoaSaudavel c : contaminados) {
            pessoasSaudaveis.remove(c);
        }

        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();
        long dataAtual = data.getTime();

        ArrayList<PessoaDoente> novosZumbis = new ArrayList<>();
        for (PessoaDoente pd : pessoasDoentes) {
            long tempo = dataAtual - pd.getDataDeContagio();
            int corZumbi = 0;
            try {
                corZumbi = indexCor(cores, ICores.ZUMBI);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            if (tempo >= 15000) {
                zumbis.add(new Zumbi(pd.getX(), pd.getY(), corZumbi, virus));
                novosZumbis.add(pd);
            }
        }

        for (PessoaDoente nz : novosZumbis) {
            pessoasDoentes.remove(nz);
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
                System.out.print(cores[mapa[i][j]] + " " + cores[7]);
            }
            System.out.print("\n");
        }
        System.out.println("");
    }

    public void desenhaMundoII() {
        for (int i = 0; i < this.mapa.length; i++) {
            for (int j = 0; j < this.mapa[0].length; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.print("\n");
        }
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

    private int indexCor(String vetor[], String cor) throws Exception {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].equals(cor)) {
                return i;
            }
        }
        throw new Exception("Cor não encontrada");
    }
}
