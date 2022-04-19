package projetoviruszumbi;

/**
 * Interface ICores. Define constantes para as cores usadas
 *
 * @author Jean-Luc Bonnet
 */
public interface ICores {

    /**
     * Cor dos espaços vazios
     */
    public static final String VAZIO = "\u001B[40m";
    /**
     * Cor da borda
     */
    public static final String BORDA = "\u001B[46m";
    /**
     * Cor da pessoa saudável
     */
    public static final String PESSOA_SAUDAVEL = "\u001B[45m";
    /**
     * Cor da pessoa saudável
     */
    public static final String PESSOA_DOENTE = "\u001B[44m";
    /**
     * Cor do zumbi
     */
    public static final String ZUMBI = "\u001B[42m";
    /**
     * Cor das paredes do hospital
     */
    public static final String HOSPITAL_PAREDES = "\u001B[47m";
    /**
     * Cor da cruz do hospital
     */
    public static final String HOSPITAL_CRUZ = "\u001B[41m";
    /**
     * Reset das cores
     */
    public static final String RESET = "\u001B[0m";
}
