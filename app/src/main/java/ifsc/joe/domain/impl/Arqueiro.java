package ifsc.joe.domain.impl;

import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;
import ifsc.joe.enums.Recursos;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Arqueiro extends Personagem {
    public static final String NOME_IMAGEM = "arqueiro";
    private int nro_flechas;
    HashMap<Recursos, Integer> estoque;

    public Arqueiro(int x, int y) {
        super(Constantes.VIDA_ARQUEIRO,Constantes.ATAQUE_ARQUEIRO,Constantes.VELOCIDADE_ARQUEIRO,x,y);
        this.icone = this.carregarImagem(NOME_IMAGEM);
    }

    public void desenhar(Graphics g, JPanel painel) {
        this.icone = this.carregarImagem(NOME_IMAGEM);
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }

    public int getNro_flechas() {
        return nro_flechas;
    }
    public String getEstoque() {
        return String.format("-%s:%d\n-%s:%d\n", Recursos.COMIDA,this.estoque.get(Recursos.COMIDA),Recursos.MADEIRA,this.estoque.get(Recursos.MADEIRA));
    }
}
