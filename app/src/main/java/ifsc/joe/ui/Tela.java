package ifsc.joe.ui;

import ifsc.joe.api.Guerreiro;
import ifsc.joe.api.comMontaria;
import ifsc.joe.core.Personagem;
import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.domain.impl.RecursoManager;
import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.TipoPersonagem;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static ifsc.joe.domain.impl.PersonagemFactory.criar;

public class Tela extends JPanel{
    private final Set<Personagem> personagens;
    private RecursoManager cache_img;
    public Tela() {
        this.setBackground(Color.white);

        //Container dos personagems
        this.personagens = new HashSet<>();
        cache_img = new RecursoManager();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //desenha qualquer personagem na tela
        this.personagens.forEach(personagem -> personagem.desenhar(g,this));
        g.dispose();
    }

    public void criarPersonagem(TipoPersonagem tipo, int x, int y){
        Personagem p = switch (tipo) {
            case TipoPersonagem.ALDEAO -> (Aldeao) criar(tipo, x, y);
            case TipoPersonagem.ARQUEIRO -> (Arqueiro) criar(tipo, x, y);
            case TipoPersonagem.CAVALEIRO -> (Cavaleiro) criar(tipo, x, y);
        };
        p.desenhar(super.getGraphics(), this);
        personagens.add(p);
    }


    public void moverPersonagens(String p,Direcao direcao) {
        Class<?> classe_filtro = null;
        if (p != null) {
            classe_filtro = switch (p) {
                case "ALDEAO" -> Aldeao.class;
                case "ARQUEIRO" -> Arqueiro.class;
                case "CAVALEIRO" -> Cavaleiro.class;
                default -> classe_filtro;
            };
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
            classe_filtro = switch (p) {
                case "ALDEAO" -> Aldeao.class;
                case "ARQUEIRO" -> Arqueiro.class;
                case "CAVALEIRO" -> Cavaleiro.class;
                default -> classe_filtro;
            };
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
        Class<?> classe_filtro = null;
        if (p != null) {
            classe_filtro = switch (p) {
                case "ALDEAO" -> Aldeao.class;
                case "ARQUEIRO" -> Arqueiro.class;
                case "CAVALEIRO" -> Cavaleiro.class;
                default -> classe_filtro;
            };
        }
        for (Personagem personagem : personagens) {
            if ((classe_filtro == null) || (classe_filtro.isInstance(personagem))) {
                if(personagem instanceof comMontaria) {
                    ((comMontaria)personagem).alternarMontado();
                }

            }


        }
        this.repaint();
    }


}