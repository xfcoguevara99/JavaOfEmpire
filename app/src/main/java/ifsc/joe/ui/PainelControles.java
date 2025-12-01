package ifsc.joe.ui;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.util.HashMap;
import java.util.Random;

/**
 * Classe responsável por gerenciar os controles e interações da interface.
 * Conecta os componentes visuais com a lógica do jogo (Tela).
 */
public class PainelControles {

    private final Random sorteio;
    private Tela tela;

    // Componentes da interface (gerados pelo Form Designer)
    private JPanel painelPrincipal;
    private JPanel painelTela;
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

    public PainelControles() {
        this.sorteio = new Random();
        configurarListeners();
    }

    /**
     * Configura todos os listeners dos botões.
     */
    private void configurarListeners() {
        configurarBotoesMovimento();
        configurarBotoesCriacao();
        configurarBotaoAtaque();
    }

    /**
     * Configura todos os listeners dos botões de movimento
     */
    private void configurarBotoesMovimento() {
        buttonCima.addActionListener(e -> getTela().movimentarAldeoes(Direcao.CIMA));
        buttonBaixo.addActionListener(e -> getTela().movimentarAldeoes(Direcao.BAIXO));
        buttonEsquerda.addActionListener(e -> getTela().movimentarAldeoes(Direcao.ESQUERDA));
        buttonDireita.addActionListener(e -> getTela().movimentarAldeoes(Direcao.DIREITA));
    }

    /**
     * Configura todos os listeners dos botões de criação
     */
    private void configurarBotoesCriacao() {
        bCriaAldeao.addActionListener(e -> criarAldeaoAleatorio());

        bCriaArqueiro.addActionListener(e ->criarArqueiroAleatorio());

        bCriaCavaleiro.addActionListener(e -> {
            //TODO: Implementar criação de cavaleiro
            mostrarMensagemNaoImplementado("Criar Cavaleiro");
        });
    }

    /**
     * Configura o listener do botão de ataque
     */
    private void configurarBotaoAtaque() {
        atacarButton.addActionListener(e -> getTela().atacarAldeoes());
    }

    /**
     * Cria um aldeão em posição aleatória na tela.
     */

    private void criarAldeaoAleatorio() {
        getTela().criarAldeao(posicaoRandomTela().get("HORIZONTAL"),posicaoRandomTela().get("VERTICAL"));
    }
    private void criarArqueiroAleatorio() {
        getTela().criarArqueiro(posicaoRandomTela().get("HORIZONTAL"),posicaoRandomTela().get("VERTICAL"));
    }

    /**
     * Exibe mensagem informando que a funcionalidade ainda não foi implementada.
     */
    private void mostrarMensagemNaoImplementado(String funcionalidade) {
        JOptionPane.showMessageDialog(
                painelPrincipal,
                "Preciso ser implementado",
                funcionalidade,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Obtém a referência da Tela com cast seguro.
     */
    private Tela getTela() {
        if (tela == null) {
            tela = (Tela) painelTela;
        }
        return tela;
    }

    /**
     * Retorna o painel principal para ser adicionado ao JFrame.
     */
    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }

    /**
     * Método chamado pelo Form Designer para criar componentes customizados.
     * Este método é invocado antes do construtor.
     */
    private void createUIComponents() {
        this.painelTela = new Tela();
    }

    //metodo privado para criar a posição aleatoria na tela
    private HashMap<String,Integer> posicaoRandomTela() {
        HashMap<String,Integer> coordenadas = new HashMap<>();
        final int PADDING = 50;
        coordenadas.put("HORIZONTAL",sorteio.nextInt(painelTela.getWidth() - PADDING));
        coordenadas.put("VERTICAL",sorteio.nextInt(painelTela.getHeight() - PADDING));
        return coordenadas;
    }
}