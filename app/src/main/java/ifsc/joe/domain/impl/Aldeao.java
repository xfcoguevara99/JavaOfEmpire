package ifsc.joe.domain.impl;

import ifsc.joe.api.Coletador;
import ifsc.joe.api.comMontaria;
import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;
import ifsc.joe.enums.Recursos;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class Aldeao extends Personagem implements Coletador, comMontaria {

    public static final String NOME_IMAGEM = "aldeao";
    private boolean noCavalho;
    private HashMap<Recursos, Integer> estoque;

    public Aldeao(int x, int y) {
        super(Constantes.VIDA_ALDEAO,Constantes.ATAQUE_ALDEAO,Constantes.VELOCIDADE_ALDEAO,x,y);
        this.icone = this.carregarImagem(NOME_IMAGEM);
        this.noCavalho = false;
        this.estoque = new HashMap<>();
        this.estoque.put(Recursos.COMIDA,0);
        this.estoque.put(Recursos.OURO,0);
    }

    //implementações de interfaces

    @Override
    public void coletar(Recursos recurso){
        if(this.estoque.containsKey(recurso)){
            this.estoque.put(recurso,this.estoque.get(recurso)+1);
        }
    }



    //Implementação metodos abstractos
    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        this.icone = this.carregarImagem(NOME_IMAGEM + (noCavalho ? "2" : "1"));
        // desenhando de fato a imagem no pai
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }

    @Override
    public void alternarMontado() {
        this.noCavalho = !this.noCavalho;
        this.velocidade = this.noCavalho ? Constantes.VELOCIDADE_ALDEAO*2 : Constantes.VELOCIDADE_ALDEAO;
    }
}