package ifsc.joe.ui;

import ifsc.joe.api.Guerreiro;
import ifsc.joe.api.comMontaria;
import ifsc.joe.core.Personagem;
import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.TipoPersonagem;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static ifsc.joe.domain.impl.PersonagemFactory.criar;

public class Tela extends JPanel{
    private final Set<Personagem> personagens;

    public Tela() {
        this.setBackground(Color.white);
        //Container dos personagems
        this.personagens = new HashSet<>();

    }

    /**
     * Method que invocado sempre que o JPanel precisa ser resenhado.
     * @param g Graphics componente de java.awt
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //desenha qualquer personagem na tela
        this.personagens.forEach(personagem -> personagem.desenhar(g,this));
        g.dispose();
    }

    /**
     * Cria um aldeao nas coordenadas X e Y, desenha-o neste JPanel
     * e adiciona o mesmo na lista de aldeoes
     *
     * @param x coordenada X
     * @param y coordenada Y
     */


//    public void criarAldeao(int x, int y) {
//        Aldeao a = new Aldeao(x, y);
//        a.desenhar(super.getGraphics(), this);
//        this.personagens.add(a);
//    }
//
//    public void criarArqueiro(int x, int y) {
//        Arqueiro a = new Arqueiro(x, y);
//        a.desenhar(super.getGraphics(), this);
//        this.personagens.add(a);
//    }
//    public void criarCavaleiro(int x, int y) {
//        Cavaleiro a = new Cavaleiro(x, y);
//        a.desenhar(super.getGraphics(), this);
//        this.personagens.add(a);
//    }
    public void criarPersonagem(TipoPersonagem tipo, int x, int y){
        Personagem p = switch (tipo) {
            case TipoPersonagem.ALDEAO -> (Aldeao) criar(tipo, x, y);
            case TipoPersonagem.ARQUEIRO -> (Arqueiro) criar(tipo, x, y);
            case TipoPersonagem.CAVALEIRO -> (Cavaleiro) criar(tipo, x, y);
        };
        p.desenhar(super.getGraphics(), this);
        personagens.add(p);
    }

    /**
     * Atualiza as coordenadas X ou Y de todos os aldeoes
     *
     * @param direcao direcao para movimentar
     */

    public void moverPersonagens(String p,Direcao direcao) {
        Class<?> classe_filtro = null;
        if (p != null) {
            switch (p) {
                case "ALDEAO":
                    classe_filtro = Aldeao.class;
                    break;
                case "ARQUEIRO":
                    classe_filtro = Arqueiro.class;
                    break;
                case "CAVALEIRO":
                    classe_filtro = Cavaleiro.class;
                    break;
            }
        }
        for (Personagem personagem : personagens) {
            if ((classe_filtro == null) || (classe_filtro.isInstance(personagem))) {
                personagem.mover(direcao, this.getWidth(), this.getHeight());
            }
        }
        this.repaint();
    }

    public void atacarGuerreiros(String p) {
        Class<?> classe_filtro = null;
        if (p != null) {
            switch (p) {
                case "ALDEAO":
                    classe_filtro = Aldeao.class;
                    break;
                case "ARQUEIRO":
                    classe_filtro = Arqueiro.class;
                    break;
                case "CAVALEIRO":
                    classe_filtro = Cavaleiro.class;
                    break;
            }
        }
        for (Personagem personagem : personagens) {
            if ((classe_filtro == null) || (classe_filtro.isInstance(personagem))) {
                if(personagem instanceof Guerreiro) {
                    ((Guerreiro)personagem).atacar();
                }
            }
        }
        this.repaint();
    }

    public void montarNoCavalo(String p){
        for (Personagem personagem : personagens) {
            if ((p == "CAVALEIRO" || p == null) && personagem instanceof Cavaleiro) {
                ((comMontaria)personagem).alternarMontado();
            }
        }
        this.repaint();

    }


}