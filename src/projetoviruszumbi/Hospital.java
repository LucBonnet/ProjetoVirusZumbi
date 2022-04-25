package projetoviruszumbi;

/**
 * Classe Hospital. Fornece método e satributos para o registro de um hospital
 *
 * @author Jean-Luc Bonnet
 */
public class Hospital {

    private int corParede;
    private int corCruz;
    private int largura;
    private int altura;
    private int x;
    private int y;

    /**
     * Método construtor parametrizado da classe Hospital
     *
     * @param x posição x do hospital
     * @param y posição y do hospital
     * @param largura largura do hospital
     * @param altura altura do hospital
     * @param corP cor das paredes do hospital
     * @param corC cor da cruz do hospital
     */
    public Hospital(int x, int y, int largura, int altura, int corP, int corC) {
        this.corParede = corP;
        this.corCruz = corC;
        this.largura = largura;
        this.altura = altura;
        this.x = x;
        this.y = y;
    }

    /**
     * Método getX. Retorna o x da posição do hospital (canto superior esquerdo)
     *
     * @return x da posição do hospital
     */
    public int getX() {
        return x;
    }

    /**
     * Método setX. Registra o x da posição do hospital (canto superior
     * esquerdo)
     *
     * @param x x da posição do hospital
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Método getY. Retorna o y da posição do hospital (canto superior esquerdo)
     *
     * @return y da posição do hospital
     */
    public int getY() {
        return y;
    }

    /**
     * Método setY. Registra o y da posição do hospital (canto superior
     * esquerdo)
     *
     * @param y y da posição do hospital
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Método getCorParede. Retorna a cor da parede do hospital
     *
     * @return cor da parede do hospital
     */
    public int getCorParede() {
        return this.corParede;
    }

    /**
     * Método setCorParede. Registra a cor da parede do hospital
     *
     * @param cor cor da parede do hospital
     */
    public void setCorParede(int cor) {
        this.corParede = cor;
    }

    /**
     * Método getCorCruz. Retorna a cor da cruz do hospital
     *
     * @return cor da cruz do hospital
     */
    public int getCorCruz() {
        return this.corCruz;
    }

    /**
     * Método setCorCruz. Registra a cor da cruz do hospital
     *
     * @param cor cor da cruz do hospital
     */
    public void setCorCruz(int cor) {
        this.corCruz = cor;
    }

    /**
     * Método getLargura. Retorna a largura do hospital
     *
     * @return largura do hospital
     */
    public int getLargura() {
        return this.largura;
    }

    /**
     * Método setLargura. Registra a largura do hospital
     *
     * @param largura largura do hospital
     */
    public void setLargura(int largura) {
        this.largura = largura;
    }

    /**
     * Método getAltura. Retorna a altura do hospital
     *
     * @return altura do hospital
     */
    public int getAltura() {
        return this.altura;
    }

    /**
     * Método setAltura. Registra a altura do hospital
     *
     * @param altura altura do hospital
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }
}
