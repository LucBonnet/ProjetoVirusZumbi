package projetoviruszumbi;

/**
 * Classe ProjetoVirusZumbi. Classe Principal.
 *
 * @author Jean-Luc Bonnet
 */
public class ProjetoVirusZumbi {
    /**
     * Método inicial
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mundo m = new Mundo();
        while (true) {
            // Atualiza o mundo
            m.atualizaMundo();
            // Desenha o mundo
            m.desenhaMundo();

            try {
                // Pausa de 300ms
                Thread.sleep(300);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
