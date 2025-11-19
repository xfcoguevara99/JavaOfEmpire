package ifsc.joe;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.util.Random;

public class App {
    private JPanel painelTela;
    private JPanel principal;
    private JPanel painelLateral;
    private JButton bCriaAldeao;
    private JButton bCriaArqueiro;
    private JButton bCriaCavaleiro;
    private JRadioButton todosRadioButton;
    private JRadioButton aldeaoRadioButton;
    private JRadioButton arqueiroRadioButton;
    private JRadioButton cavaleiroRadioButton;
    private JButton atacarButton;
    private JButton buttonCima;
    private JButton buttonEsquerda;
    private JButton buttonBaixo;
    private JButton buttonDireita;
    private JLabel logo;
    private final Random sorteio;

    private App() {
        this.sorteio = new Random();

        atacarButton.addActionListener(e -> {
            //TODO preciso ser melhorado

            ((Tela) painelTela).atacarAldeoes();
        });

        buttonCima.addActionListener(e -> {
            //TODO preciso ser melhorado

            ((Tela) painelTela).movimentarAldeoes(Direcao.CIMA);
        });

        buttonBaixo.addActionListener(e -> {
            //TODO preciso ser melhorado

            ((Tela) painelTela).movimentarAldeoes(Direcao.BAIXO);
        });

        buttonEsquerda.addActionListener(e -> {
            //TODO preciso ser melhorado

            ((Tela) painelTela).movimentarAldeoes(Direcao.ESQUERDA);
        });

        buttonDireita.addActionListener(e -> {
            //TODO preciso ser melhorado

            ((Tela) painelTela).movimentarAldeoes(Direcao.DIREITA);
        });

        bCriaAldeao.addActionListener(e -> {
            // criando o aldeao em um local aleatorio
            final int PADDING = 50;
            int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
            int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

            // invocando o método criarAldeao da classe Tela.
            ((Tela) painelTela).criarAldeao(posX, posY);
        });

        bCriaArqueiro.addActionListener(e -> {
            //TODO preciso ser implementado
            JOptionPane.showMessageDialog(
                    null,
                    "Preciso ser implementado",
                    "Criar Arqueiro",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        bCriaCavaleiro.addActionListener(e -> {
            //TODO preciso ser implementado
            JOptionPane.showMessageDialog(
                    null,
                    "Preciso ser implementado",
                    "Criar Cavaleiro",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Java of Empires");
        frame.setContentPane((new App()).principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // não será possível redimensionar a janela
        frame.setResizable(false);
        frame.pack();

        // janela deverá ficar centralizada
        frame.setLocationRelativeTo(null);

        // deve ser visível
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // define o painel da tela como um objeto do tipo Tela
        this.painelTela = new Tela();
    }
}
