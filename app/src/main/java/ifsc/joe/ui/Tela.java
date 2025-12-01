package ifsc.joe.ui;

import ifsc.joe.core.Personagem;
import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Tela extends JPanel {
    private final Set<Personagem> personagens;
    //private final Set<Aldeao> aldeoes;
    //private final Set<Arqueiro> arqueiros;
    //private final Set<Cavaleiro> cavaleiros;


    public Tela() {
        //TODO preciso ser melhorado

        this.setBackground(Color.white);

        //provavelmente vai ser colocados num container so
        //this.aldeoes = new HashSet<>();
        //this.arqueiros = new HashSet<>();
        //this.cavaleiros = new HashSet<>();
        this.personagens = new HashSet<>();
    }

    /**
     * Method que invocado sempre que o JPanel precisa ser resenhado.
     * @param g Graphics componente de java.awt
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //TODO preciso ser melhorado

        // percorrendo a lista de aldeões e pedindo para cada um se desenhar na tela
        this.personagens.forEach(personagem -> personagem.desenhar(g,this));
        //this.aldeoes.forEach(aldeao -> aldeao.desenhar(g, this));
        //this.arqueiros.forEach(arqueiro -> arqueiro.desenhar(g, this));
        //this.cavaleiros.forEach(cavaleiro -> cavaleiro.desenhar(g, this));
        // liberando o contexto gráfico
        g.dispose();
    }

    /**
     * Cria um aldeao nas coordenadas X e Y, desenha-o neste JPanel
     * e adiciona o mesmo na lista de aldeoes
     *
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void criarAldeao(int x, int y) {
        Aldeao a = new Aldeao(x, y);
        a.desenhar(super.getGraphics(), this);
        this.personagens.add(a);
        //this.aldeoes.add(a);
    }
    public void criarArqueiro(int x, int y) {
        Arqueiro a = new Arqueiro(x, y);
        a.desenhar(super.getGraphics(), this);
        this.personagens.add(a);
        //this.arqueiros.add(a);
    }
    public void criarCavalheiro(int x, int y) {
        Cavaleiro a = new Cavaleiro(x, y);
        a.desenhar(super.getGraphics(), this);
        this.personagens.add(a);
        //this.cavaleiros.add(a);
    }


    /**
     * Atualiza as coordenadas X ou Y de todos os aldeoes
     *
     * @param direcao direcao para movimentar
     */
    public void movimentarAldeoes(Direcao direcao) {
        //TODO preciso ser melhorado
        for(Personagem personagem:personagens){
            if(personagem instanceof Aldeao){
                personagem.mover(direcao,this.getWidth(), this.getHeight());
            }
        }
//        this.personagens.forEach(aldeao -> aldeao.mover());
//        this.aldeoes.forEach(aldeao -> aldeao.mover(direcao, this.getWidth(), this.getHeight()));

        // Depois que as coordenadas foram atualizadas é necessário repintar o JPanel
        this.repaint();
    }

    public void moverPersonagens(String p,Direcao direcao) {
        if(p == null){
            for(Personagem i:personagens){
                i.mover(direcao,this.getWidth(), this.getHeight());
            }
        } else if(p.equals("ALDEAO")){
            for(Personagem i:personagens){
                if(i instanceof Aldeao){
                    i.mover(direcao,this.getWidth(), this.getHeight());
                }
            }
        } else if (p.equals("ARQUEIRO")) {
            for(Personagem i:personagens){
                if(i instanceof Arqueiro){
                    i.mover(direcao,this.getWidth(), this.getHeight());
                }
            }
        }else if (p.equals("CAVALEIRO")) {
            for(Personagem i:personagens){
                if(i instanceof Cavaleiro){
                    i.mover(direcao,this.getWidth(), this.getHeight());
                }
            }
        }
        this.repaint();
    }

    /**
     * Altera o estado do aldeão de atacando para não atacando e vice-versa
     */
    public void atacarAldeoes() {

        //TODO preciso ser melhorado

        // Percorrendo a lista de aldeões e pedindo para todos atacarem
        for(Personagem personagem:personagens){
            if(personagem instanceof Aldeao){
                ((Aldeao) personagem).atacar();
            }
        }
        //this.aldeoes.forEach(Aldeao::atacar);

        // Fazendo o JPanel ser redesenhado
        this.repaint();
    }
}