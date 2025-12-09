package ifsc.joe.domain.impl;

import ifsc.joe.api.Guerreiro;
import ifsc.joe.api.comMontaria;
import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;
import ifsc.joe.enums.TipoPersonagem;

import javax.swing.*;
import java.awt.*;

import static ifsc.joe.domain.impl.ImageCache.*;

public class Cavaleiro extends Personagem implements Guerreiro, comMontaria {
    private boolean noCavalho;
    private boolean atacando;

    public Cavaleiro(int x, int y) {
        super(Constantes.VIDA_CAVALEIRO,Constantes.ATAQUE_CAVALEIRO,Constantes.VELOCIDADE_CAVALEIRO,x,y);
        this.noCavalho = true;
        this.atacando = false;
        if(!imagens.containsKey(TipoPersonagem.CAVALEIRO.toString().toLowerCase())){
            carregarCache(TipoPersonagem.CAVALEIRO.toString().toLowerCase());
        }
        this.icone = getImagem(TipoPersonagem.CAVALEIRO.toString().toLowerCase());
    }

    //Implementação metodos abstractos
    @Override
    public void desenhar(Graphics g, JPanel painel) {
        String nomeImagem;
        if(noCavalho){
            nomeImagem = TipoPersonagem.CAVALEIRO.toString().toLowerCase() + (atacando ? "4" : "3");
        }else{
            nomeImagem = TipoPersonagem.CAVALEIRO.toString().toLowerCase() + (atacando ? "2" : "1");
        }
        if(!imagens.containsKey(nomeImagem)){
            carregarCache(nomeImagem);
        }
        this.icone = getImagem(nomeImagem);
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
