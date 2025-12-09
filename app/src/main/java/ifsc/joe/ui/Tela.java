package ifsc.joe.ui;

import ifsc.joe.api.Coletador;
import ifsc.joe.api.Guerreiro;
import ifsc.joe.api.comMontaria;
import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;
import ifsc.joe.domain.impl.*;
import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.Recursos;
import ifsc.joe.enums.TipoPersonagem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static ifsc.joe.domain.impl.PersonagemFactory.criar;

public class Tela extends JPanel{
    private final Set<Personagem> personagens;
    private ImageCache cache_img; //Classe que contem o repositorio de imagens
    private Recurso recursoNaTela;
    private HashMap<String,Integer> recursosColetados;
    public Tela() {
        this.setBackground(Color.white);
        //Container dos personagems
        this.personagens = new HashSet<>();
        cache_img = new ImageCache();
        recursosColetados = new HashMap<>();
        recursosColetados.put(Recursos.OURO.toString(), 0);
        recursosColetados.put(Recursos.COMIDA.toString(), 0);
        recursosColetados.put(Recursos.MADEIRA.toString(), 0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //desenha qualquer personagem na tela
        this.personagens.forEach(personagem -> personagem.desenhar(g,this));
        if(this.recursoNaTela != null){
            this.recursoNaTela.desenhar(g,this);
        }
        g.dispose();
    }

    public void criarPersonagem(TipoPersonagem tipo, int x, int y){
        Personagem p = switch (tipo) {
            case TipoPersonagem.ALDEAO -> (Aldeao) criar(tipo, x, y);
            case TipoPersonagem.ARQUEIRO -> (Arqueiro) criar(tipo, x, y);
            case TipoPersonagem.CAVALEIRO -> (Cavaleiro) criar(tipo, x, y);
        };
        //p.desenhar(super.getGraphics(), this);
        personagens.add(p);
        this.repaint();
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

    private HashMap<String,Integer> pontoMedio(Object objeto){

        HashMap<String,Integer> coordenada = new HashMap<>();
        if(objeto instanceof Personagem){
            coordenada.put("x",((Personagem) objeto).getIcone().getWidth(null)/2 + ((Personagem) objeto).getPosX());
            coordenada.put("y",((Personagem) objeto).getIcone().getWidth(null)/2 + ((Personagem) objeto).getPosY());
        }else if(objeto instanceof Recurso){
            coordenada.put("x",((Recurso) objeto).getIcone().getWidth(null)/2 + ((Recurso) objeto).getPosX());
            coordenada.put("y",((Recurso) objeto).getIcone().getWidth(null)/2 + ((Recurso) objeto).getPosY());

        }

        return coordenada;
    }

    private boolean distanciaEntreObjetos(Object obj1,Object obj2){
        HashMap<String,Integer> coordenadaObj1 = pontoMedio(obj1);
        HashMap<String,Integer> coordenadaObj2 = pontoMedio(obj2);
        double distancia = Math.hypot(Math.abs(coordenadaObj1.get("x")-coordenadaObj2.get("x")), Math.abs(coordenadaObj1.get("y")-coordenadaObj2.get("y")));
        if(distancia< Constantes.RAIO_COLETA){
            System.out.println(distancia);
            return true;
        }
        return false;
    }


    public void criarRecursos(Recursos tipo,int x,int y){
        this.recursoNaTela = new Recurso(tipo,x,y);
        this.repaint();

    }

    public void colherRecursos(String p){
        if(recursoNaTela == null){return;}
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
                if((personagem instanceof Coletador) && this.distanciaEntreObjetos(personagem,recursoNaTela)){
                    if(((Coletador) personagem).coletar(recursoNaTela.getTipo())){
                        this.recursosColetados.put(recursoNaTela.getTipo().toString(),this.recursosColetados.get(recursoNaTela.getTipo().toString())+1);
                        recursoNaTela = null;
                        break;
                    }

                }
            }
        }
        this.repaint();
    }
     public HashMap<String,Integer> getRecursoNaTela(){
        return this.recursosColetados;
     }


}