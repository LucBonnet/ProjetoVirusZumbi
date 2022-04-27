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
    public void mover(int larguraMapa, int alturaMapa) {
        // Define uma direção
        int direcao = (int) (Math.random() * 4);

        // Move na direção
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
        
        // Verifica se a pessoa está nas bordas do mundo no eixo x
        if (super.getX() > larguraMapa - 1) {
            super.setX(0);
        } else if (super.getX() < 0) {
            super.setX(larguraMapa - 1);
        }

        // Verifica se a pessoa está nas bordas do mundo no eixo y
        if (super.getY() > alturaMapa - 1) {
            super.setY(0);
        } else if (super.getY() < 0) {
            super.setY(alturaMapa - 1);
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
