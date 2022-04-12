package projetoviruszumbi;

/**
 * Classe PessoaSaudavel. Fornece métodos e atributos para o registro de uma
 * pessoa saudável
 *
 * @author Jean-Luc Bonnet
 */
public class PessoaSaudavel extends Pessoa implements IMovable {

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

    /**
     * Método construtor da classe PessoaSaudavel
     *
     * @param x x da posição da Pessoa
     * @param y y da posição da Pessoa
     * @param cor cor da Pessoa
     */
    public PessoaSaudavel(int x, int y, int cor) {
        super(x, y, cor);
    }
}
