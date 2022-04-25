package projetoviruszumbi;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe PessoaDoente. Fornece métodos e atributos para o registro de uma
 * pessoa doente
 *
 * @author Jean-Luc Bonnet
 */
public class PessoaDoente extends Pessoa implements IMovable {

    private Virus virus;
    private long dataDeContagio;

    /**
     * Métoo construtor da classe Pessoa
     *
     * @param x x da posição da Pessoa
     * @param y y da posição da Pessoa
     * @param cor cor da Pessoa
     * @param virus vírus da Pessoa
     */
    public PessoaDoente(int x, int y, int cor, Virus virus) {
        super(x, y, cor);
        this.virus = virus;
        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();
        this.dataDeContagio = data.getTime();
    }

    /**
     * Método mover. Move a pessoa para uma posição aleatória
     */
    @Override
    public void mover(int alturaMapa, int larguraMapa) {
        // Define uma direção
        int direcao = (int) (Math.random() * 4);

        switch (direcao) {
            case 0:
                setY(getY() - 1);
                break;
            case 1:
                setY(getY() + 1);
                break;
            case 2:
                setX(getX() - 1);
                break;
            case 3:
                setX(getX() + 1);
                break;
        }

        if (super.getX() > larguraMapa - 1) {
            super.setX(0);
        } else if (super.getX() < 0) {
            super.setX(larguraMapa - 1);
        }

        if (super.getY() > alturaMapa - 1) {
            super.setY(0);
        } else if (super.getY() < 0) {
            super.setY(alturaMapa - 1);
        }
    }

    public long getDataDeContagio() {
        return this.dataDeContagio;
    }
}
