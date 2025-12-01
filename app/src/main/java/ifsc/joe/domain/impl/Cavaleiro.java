package ifsc.joe.domain.impl;

import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;

import javax.swing.*;
import java.awt.*;

public class Cavaleiro extends Personagem {
    protected boolean noCavalho;
    public static final String NOME_IMAGEM = "cavaleiro";

    public Cavaleiro(int x, int y) {
        super(Constantes.VIDA_CAVALEIRO,Constantes.ATAQUE_CAVALEIRO,Constantes.VELOCIDADE_CAVALEIRO,x,y);
        noCavalho = true;
    }

    public void desenhar(Graphics g, JPanel painel) {
        this.icone = this.carregarImagem(NOME_IMAGEM);
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }

    public boolean isNoCavalho() {return noCavalho;}
}
