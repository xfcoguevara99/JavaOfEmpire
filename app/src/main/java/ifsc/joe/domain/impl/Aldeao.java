package ifsc.joe.domain.impl;

import ifsc.joe.api.Coletador;
import ifsc.joe.api.comMontaria;
import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;
import ifsc.joe.enums.Recursos;
import ifsc.joe.enums.TipoPersonagem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import static ifsc.joe.domain.impl.ImageCache.carregarCache;
import static ifsc.joe.domain.impl.ImageCache.getImagem;
import static ifsc.joe.domain.impl.ImageCache.imagens;


public class Aldeao extends Personagem implements Coletador, comMontaria {

    //public static final String NOME_IMAGEM = "aldeao";
    private boolean noCavalho;
    private HashMap<Recursos, Integer> estoque;

    public Aldeao(int x, int y) {
        super(Constantes.VIDA_ALDEAO,Constantes.ATAQUE_ALDEAO,Constantes.VELOCIDADE_ALDEAO,x,y);
        this.noCavalho = false;
        if(!imagens.containsKey(TipoPersonagem.ALDEAO.toString().toLowerCase())){
            carregarCache(TipoPersonagem.ALDEAO.toString().toLowerCase());
        }
        this.icone = getImagem(TipoPersonagem.ALDEAO.toString().toLowerCase());
        this.estoque = new HashMap<>();
        this.estoque.put(Recursos.COMIDA,0);
        this.estoque.put(Recursos.OURO,0);
    }

    //implementações de interfaces

    @Override
    public boolean coletar(Recursos recurso){
        if(this.estoque.containsKey(recurso)){
            this.estoque.put(recurso,this.estoque.get(recurso)+1);
            return true;
        }
        return false;
    }



    //Implementação metodos abstractos
    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        String nomeImagem = TipoPersonagem.ALDEAO.toString().toLowerCase() + (noCavalho ? "2" : "1");
        if(!imagens.containsKey(nomeImagem)){
            carregarCache(nomeImagem);
        }
        this.icone = getImagem(nomeImagem);
        // desenhando de fato a imagem no pai
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }


    @Override
    public void alternarMontado() {
        this.noCavalho = !this.noCavalho;
        this.velocidade = this.noCavalho ? Constantes.VELOCIDADE_ALDEAO*2 : Constantes.VELOCIDADE_ALDEAO;
    }
}