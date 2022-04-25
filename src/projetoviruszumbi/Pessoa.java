package projetoviruszumbi;

/**
 * Classe abstrata Pessoa. Fornece métodos e atributos para o registro de uma
 * pessoa
 *
 * @author Jean-Luc Bonnet
 */
public abstract class Pessoa {

    private int x;
    private int y;
    private int cor;

    /**
     * Método construtor parametrizado da classe Pessoa
     *
     * @param x posição x da pessoa
     * @param y posição y da pessoa
     * @param cor corda pessoa
     */
    public Pessoa(int x, int y, int cor) {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }

    /**
     * Método construtor da classe Pessoa
     */
    public Pessoa() {

    }

    /**
     * Método getX. Retorna o x da poisção da Pessoa
     *
     * @return x da poisção da Pessoa
     */
    public int getX() {
        return this.x;
    }

    /**
     * Método setX. Registra o x da posição da Pessoa
     *
     * @param x x da posição da Pessoa
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Método getY. Retorna o y da poisção da Pessoa
     *
     * @return y da poisção da Pessoa
     */
    public int getY() {
        return this.y;
    }

    /**
     * Método setY. Registra o y da posição da Pessoa
     *
     * @param y y da posição da Pessoa
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Método getCor. Retorna a cor da Pessoa
     *
     * @return cor da Pessoa
     */
    public int getCor() {
        return this.cor;
    }

    /**
     * Método setCor. Registra a cor da Pessoa
     *
     * @param cor corda Pessoa
     */
    public void setCor(int cor) {
        this.cor = cor;
    }

}
