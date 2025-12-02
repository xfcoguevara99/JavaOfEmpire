package ifsc.joe.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Classe responsável pela configuração e exibição da janela principal do jogo.
 */
public class JanelaJogo extends JFrame {

    private static final String TITULO = "Java of Empires";
   // private final JFrame frame;
    private final PainelControles painelControles;

    public JanelaJogo() {
        super(TITULO);
        //this.frame = new JFrame(TITULO);
        this.painelControles = new PainelControles();
        this.configurarJanela();
    }

    /**
     * Configura o conteúdo da janela
     */
    private void configurarJanela() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(painelControles.getPainelPrincipal());
        this.pack();
        this.setLocationRelativeTo(null); // Centralizar na tela
    }

    /**
     * Torna a janela visível.
     */
    public void exibir() {
        this.setVisible(true);
        this.painelControles.getTela().setVisible(true);
    }
}