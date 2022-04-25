package projetoviruszumbi;

/**
 * Classe Zumbi. Fornece métodos e atributos para o registro de um zumbi
 *
 * @author Jean-Luc Bonnet
 */
public class Zumbi extends PessoaDoente {

    /**
     * Método construtor da classe Zumbi
     *
     * @param x x da posição do zumbi
     * @param y y da posição do zumbi
     * @param cor cor do zumbi
     * @param virus vírus do zumbi
     */
    public Zumbi(int x, int y, int cor, Virus virus) {
        super(x, y, cor, virus);
    }

}
