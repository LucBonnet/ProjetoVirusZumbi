package projetoviruszumbi;

/**
 * Classe PessoaDoente. Fornece métodos e atributos para o registro de uma
 * pessoa doente
 *
 * @author Jean-Luc Bonnet
 */
public class PessoaDoente extends Pessoa implements IMovable {

    private Virus virus;

    /**
     * Métoo construtor da classe Pessoa
     * @param x x da posição da Pessoa
     * @param y y da posição da Pessoa
     * @param cor cor da Pessoa
     * @param virus vírus da Pessoa
     */
    public PessoaDoente(int x, int y, int cor, Virus virus) {
        super(x, y, cor);
        this.virus = virus;
    }

    /**
     * Método mover. Move a pessoa para uma posição aleatória
     */
    @Override
    public void mover() {
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
    }
}
