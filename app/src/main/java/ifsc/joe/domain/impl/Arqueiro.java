package ifsc.joe.domain.impl;

import ifsc.joe.api.Coletador;
import ifsc.joe.api.Guerreiro;
import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;
import ifsc.joe.enums.Recursos;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Arqueiro extends Personagem implements Coletador, Guerreiro {
    public static final String NOME_IMAGEM = "arqueiro";
    private int nro_flechas;
    private boolean atacando;
    HashMap<Recursos, Integer> estoque;

    public Arqueiro(int x, int y) {
        super(Constantes.VIDA_ARQUEIRO,Constantes.ATAQUE_ARQUEIRO,Constantes.VELOCIDADE_ARQUEIRO,x,y);
        this.icone = this.carregarImagem(NOME_IMAGEM);
        this.atacando = false;
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
    public void coletar(Recursos recurso) {
        if(this.estoque.containsKey(recurso)){
            this.estoque.put(recurso,this.estoque.get(recurso)+1);
        }
    }

    //implementação metodos abstractos
    @Override
    public void desenhar(Graphics g, JPanel painel) {
        this.icone = this.carregarImagem(NOME_IMAGEM + (atacando ? "2" : "1"));
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }
}
