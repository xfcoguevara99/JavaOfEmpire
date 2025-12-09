package ifsc.joe.domain.impl;

import ifsc.joe.api.Coletador;
import ifsc.joe.api.Guerreiro;
import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;
import ifsc.joe.enums.Recursos;
import ifsc.joe.enums.TipoPersonagem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static ifsc.joe.domain.impl.ImageCache.*;

public class Arqueiro extends Personagem implements Coletador, Guerreiro{
    private int nro_flechas;
    private boolean atacando;
    HashMap<Recursos, Integer> estoque;

    public Arqueiro(int x, int y) {
        super(Constantes.VIDA_ARQUEIRO,Constantes.ATAQUE_ARQUEIRO,Constantes.VELOCIDADE_ARQUEIRO,x,y);
        this.atacando = false;
        if(!imagens.containsKey(TipoPersonagem.ARQUEIRO.toString().toLowerCase())){
            carregarCache(TipoPersonagem.ARQUEIRO.toString().toLowerCase());
        }
        this.icone = getImagem(TipoPersonagem.ARQUEIRO.toString().toLowerCase());
        this.nro_flechas = Constantes.FlECHAS_INICIAIS;
        this.estoque = new HashMap<>();
        this.estoque.put(Recursos.COMIDA,0);
        this.estoque.put(Recursos.MADEIRA,0);
    }



    //metodos de acceso
    public int getNro_flechas() {
        return nro_flechas;
    }

    public String getEstoque() {
        return String.format("-%s:%d\n-%s:%d\n", Recursos.COMIDA,this.estoque.get(Recursos.COMIDA),Recursos.MADEIRA,this.estoque.get(Recursos.MADEIRA));
    }


    //implementação das interfaces

    @Override
    public void atacar() {
        this.atacando = !this.atacando;
    }

    @Override
    public boolean coletar(Recursos recurso) {
        if(this.estoque.containsKey(recurso)){
            this.estoque.put(recurso,this.estoque.get(recurso)+1);
            return true;
        }
        return false;
    }

    //implementação metodos abstractos
    @Override
    public void desenhar(Graphics g, JPanel painel) {
        String nomeImagem = TipoPersonagem.ARQUEIRO.toString().toLowerCase() + (atacando ? "2" : "1");
        if(!imagens.containsKey(nomeImagem)){
            carregarCache(nomeImagem);
        }
        this.icone = getImagem(nomeImagem);
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }
}
