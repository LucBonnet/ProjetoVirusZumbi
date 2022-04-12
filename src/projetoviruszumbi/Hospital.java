package projetoviruszumbi;

/**
 * Classe Hospital. Fornece método e satributos para o registro de um hospital
 *
 * @author Jean-Luc Bonnet
 */
public class Hospital {

    private int cor;

    /**
     * Método construtor parametrizado da classe Hospital
     *
     * @param cor cor do hospital
     */
    public Hospital(int cor) {
        this.cor = cor;
    }

    /**
     * Método getCor. Retorna a cor do hospital
     *
     * @return cor do hospital
     */
    public int getCor() {
        return this.cor;
    }

    /**
     * Método setCor. Registra a cor do hospital
     *
     * @param cor cor do hospital
     */
    public void setCor(int cor) {
        this.cor = cor;
    }
}
