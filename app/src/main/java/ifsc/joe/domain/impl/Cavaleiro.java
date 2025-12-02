package ifsc.joe.domain.impl;

import ifsc.joe.api.Guerreiro;
import ifsc.joe.api.comMontaria;
import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;

import javax.swing.*;
import java.awt.*;

public class Cavaleiro extends Personagem implements Guerreiro, comMontaria {
    private boolean noCavalho;
    private boolean atacando;
    public static final String NOME_IMAGEM = "cavaleiro";

    public Cavaleiro(int x, int y) {
        super(Constantes.VIDA_CAVALEIRO,Constantes.ATAQUE_CAVALEIRO,Constantes.VELOCIDADE_CAVALEIRO,x,y);
        this.noCavalho = true;
        this.atacando = false;
    }

    //Implementação metodos abstractos
    @Override
    public void desenhar(Graphics g, JPanel painel) {
        if(noCavalho){
            this.icone = this.carregarImagem(NOME_IMAGEM + (atacando ? "2" : "1"));
        }else{
            this.icone = this.carregarImagem(NOME_IMAGEM + (atacando ? "4" : "3"));
        }
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }

    //metodos de acesso
    public boolean isNoCavalho() {return noCavalho;}


    //implementacação das interfaces
    public void alternarMontado(){
        this.noCavalho = !this.noCavalho;
        this.velocidade = this.noCavalho ? Constantes.VELOCIDADE_CAVALEIRO : Constantes.VELOCIDADE_CAVALEIRO/2;
    }

    public void atacar() {
        this.atacando = !this.atacando;
    }
}
