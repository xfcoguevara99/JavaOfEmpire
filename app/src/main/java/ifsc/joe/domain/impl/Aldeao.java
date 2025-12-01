package ifsc.joe.domain.impl;

import ifsc.joe.constantes.Constantes;
import ifsc.joe.core.Personagem;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Aldeao extends Personagem{

    public static final String NOME_IMAGEM = "aldeao";
    private boolean atacando;

    public Aldeao(int x, int y) {
        super(Constantes.VIDA_ALDEAO,Constantes.ATAQUE_ALDEAO,Constantes.VELOCIDADE_ALDEAO,x,y);
        this.icone = this.carregarImagem(NOME_IMAGEM);
        this.atacando = false;
    }

    /**
     * Desenhando o Aldeão, nas coordenadas X e Y, com a imagem 'icone'
     * no JPanel 'pai'
     *
     * @param g objeto do JPanel que será usado para desenhar o Aldeão
     */
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        this.icone = this.carregarImagem(NOME_IMAGEM + (atacando ? "2" : ""));
        // desenhando de fato a imagem no pai
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }

    public void atacar() {
        this.atacando = !this.atacando;
    }


}