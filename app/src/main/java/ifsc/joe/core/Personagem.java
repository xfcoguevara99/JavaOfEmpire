package ifsc.joe.core;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static ifsc.joe.domain.impl.Aldeao.NOME_IMAGEM;

public abstract class Personagem {
    protected int vida,ataque,posX,posY;
    protected double velocidade;
    protected Image icone;

    protected Personagem(int vida, int ataque, double velocidade, int posX, int posY) {
        this.vida = vida;
        this.ataque = ataque;
        this.velocidade = velocidade;
        this.posX = posX;
        this.posY = posY;
        this.icone = this.carregarImagem(NOME_IMAGEM);
    }

    public void sofrerDano(int dano){
        this.vida -= dano;
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        switch (direcao) {
            case CIMA     -> this.posY -= 10;
            case BAIXO    -> this.posY += 10;
            case ESQUERDA -> this.posX -= 10;
            case DIREITA  -> this.posX += 10;
        }

        //Não deixa a imagem ser desenhada fora dos limites do JPanel pai
        this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
        this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
    }

    protected Image carregarImagem(String imagem) {
        return new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("./"+imagem+".png")
        )).getImage();
    }

    //metodos abastractos
    public abstract void desenhar(Graphics g, JPanel painel);

    //metodo de acceso
    public int getVida() {return vida;}
    public int getAtaque() {return ataque;}
    public double getVelocidade() {return velocidade;}
}
