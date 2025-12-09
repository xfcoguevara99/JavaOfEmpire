package ifsc.joe.ui;

import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.Recursos;
import ifsc.joe.enums.TipoPersonagem;

import javax.swing.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Classe responsável por gerenciar os controles e interações da interface.
 * Conecta os componentes visuais com a lógica do jogo (Tela).
 */
public class PainelControles implements KeyListener{

    private final Random sorteio;
    private Tela tela;

    // Componentes da interface (gerados pelo Form Designer)
    private JPanel painelPrincipal;
    private JPanel painelTela;
    private JPanel painelLateral;
    private JButton bCriaAldeao;
    private JButton bCriaArqueiro;
    private JButton bCriaCavaleiro;
    private ButtonGroup grupoSel; //grupo de botonos seletores
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
    private JButton montarButton;
    private JButton coletarButton;

    public PainelControles() {
        this.sorteio = new Random();
        getTela().addKeyListener(this);
        getTela().setFocusable(true);
        configurarListeners();
        getTela().setFocusTraversalKeysEnabled(false); // desabilita a navegação do focus

        //agenda um evento(chamarCriarRecurso)
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this::chamarCriarRecursos, 1, 5, TimeUnit.SECONDS);
    }

    private void chamarCriarRecursos(){
        Recursos tipoRecurso = Arrays.asList(Recursos.values()).get(this.sorteio.nextInt(3));
        System.out.println(tipoRecurso.name());
        getTela().criarRecursos(tipoRecurso,posicaoRandomTela().get("HORIZONTAL"),posicaoRandomTela().get("VERTICAL"));
    }

    /**
     * Configura todos os listeners dos botões.
     */
    private void configurarListeners() {
        configurarBotoesMovimento();
        configurarBotoesCriacao();
        configurarBotaoAtaque();
        configurarFiltro();
        configurarBotaoMontar();
        configurarBotaoColetar();
    }

    private void configurarFiltro(){
        this.grupoSel = new ButtonGroup();
        aldeaoRadioButton.setActionCommand(TipoPersonagem.ALDEAO.toString());
        arqueiroRadioButton.setActionCommand(TipoPersonagem.ARQUEIRO.toString());
        cavaleiroRadioButton.setActionCommand(TipoPersonagem.CAVALEIRO.toString());
        grupoSel.add(todosRadioButton);
        grupoSel.add(aldeaoRadioButton);
        grupoSel.add(cavaleiroRadioButton);
        grupoSel.add(arqueiroRadioButton);
        todosRadioButton.setSelected(true);
    }

    private void configurarBotoesMovimento() {
        buttonCima.addActionListener(e -> getTela().moverPersonagens(grupoSel.getSelection().getActionCommand(),Direcao.CIMA));
        buttonBaixo.addActionListener(e -> getTela().moverPersonagens(grupoSel.getSelection().getActionCommand(),Direcao.BAIXO));
        buttonEsquerda.addActionListener(e -> getTela().moverPersonagens(grupoSel.getSelection().getActionCommand(),Direcao.ESQUERDA));
        buttonDireita.addActionListener(e -> getTela().moverPersonagens(grupoSel.getSelection().getActionCommand(),Direcao.DIREITA));
    }

    private void chamarCriarAldeao(){
        getTela().criarPersonagem(TipoPersonagem.ALDEAO,posicaoRandomTela().get("HORIZONTAL"),posicaoRandomTela().get("VERTICAL"));
    }
    private void chamarCriarArqueiro(){
        getTela().criarPersonagem(TipoPersonagem.ARQUEIRO,posicaoRandomTela().get("HORIZONTAL"),posicaoRandomTela().get("VERTICAL"));
    }
    private void chamarCriarCavaleiro(){
        getTela().criarPersonagem(TipoPersonagem.CAVALEIRO,posicaoRandomTela().get("HORIZONTAL"),posicaoRandomTela().get("VERTICAL"));
    }

    private void configurarBotoesCriacao() {
        bCriaAldeao.addActionListener(e -> chamarCriarAldeao());
        bCriaArqueiro.addActionListener(e -> chamarCriarArqueiro());
        bCriaCavaleiro.addActionListener(e -> chamarCriarCavaleiro());

    }

    private void configurarBotaoAtaque() {atacarButton.addActionListener(e -> getTela().atacarGuerreiros(grupoSel.getSelection().getActionCommand()));}

    private void configurarBotaoMontar(){montarButton.addActionListener(e ->getTela().montarNoCavalo(grupoSel.getSelection().getActionCommand()));}

    private void configurarBotaoColetar(){

        coletarButton.addActionListener(e -> getTela().colherRecursos(grupoSel.getSelection().getActionCommand()));
    }
    //Agrupação dos seletores
    public String getGrupoSel() {
        return grupoSel.getSelection().getActionCommand();
    }

    //metodo privado para criar a posição aleatoria na tela
    private HashMap<String,Integer> posicaoRandomTela() {
        HashMap<String,Integer> coordenadas = new HashMap<>();
        final int PADDING = 50;
        coordenadas.put("HORIZONTAL",sorteio.nextInt(painelTela.getWidth() - PADDING));
        coordenadas.put("VERTICAL",sorteio.nextInt(painelTela.getHeight() - PADDING));
        return coordenadas;
    }

    public Tela getTela() {
        if (tela == null) {
            tela = (Tela) painelTela;
        }
        return tela;
    }

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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        String tipoPersonagem = grupoSel.getSelection().getActionCommand();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_S:
                getTela().moverPersonagens(tipoPersonagem, Direcao.BAIXO);
                break;

            case KeyEvent.VK_W:
                getTela().moverPersonagens(tipoPersonagem, Direcao.CIMA);
                break;

            case KeyEvent.VK_A:
                getTela().moverPersonagens(tipoPersonagem, Direcao.ESQUERDA);
                break;

            case KeyEvent.VK_D:
                getTela().moverPersonagens(tipoPersonagem, Direcao.DIREITA);
                break;

            case KeyEvent.VK_1:
                chamarCriarAldeao();
                break;

            case KeyEvent.VK_2:
                chamarCriarArqueiro();
                break;

            case KeyEvent.VK_3:
                chamarCriarCavaleiro();
                break;

            case KeyEvent.VK_SPACE:
                getTela().atacarGuerreiros(grupoSel.getSelection().getActionCommand());
                break;
            case KeyEvent.VK_M:
                getTela().montarNoCavalo(grupoSel.getSelection().getActionCommand());
                break;
            case KeyEvent.VK_TAB:

                List<JRadioButton> a = new ArrayList<>();
                a.add(todosRadioButton);
                a.add(aldeaoRadioButton);
                a.add(arqueiroRadioButton);
                a.add(cavaleiroRadioButton);
                String p = grupoSel.getSelection().getActionCommand();
                if(p != null){
                    switch (p) {
                        case "ALDEAO":
                            arqueiroRadioButton.setSelected(true);
                            break;
                        case "ARQUEIRO":
                            cavaleiroRadioButton.setSelected(true);
                            break;
                        case "CAVALEIRO":
                            todosRadioButton.setSelected(true);
                            break;
                    }
                }else{
                    aldeaoRadioButton.setSelected(true);
                }

            default:
                // Ignorar outras teclas
                break;
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {}
}