package ifsc.joe.core;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;



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

        //this.icone = this.carregarImagem(NOME_IMAGEM);
    }

    public void sofrerDano(int dano){
        this.vida -= dano;
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        switch (direcao) {
            case CIMA     -> this.posY -= (int) (10*velocidade);
            case BAIXO    -> this.posY += (int)(10*velocidade);
            case ESQUERDA -> this.posX -= (int)(10*velocidade);
            case DIREITA  -> this.posX += (int)(10*velocidade);
        }

        //Não deixa a imagem ser desenhada fora dos limites do JPanel pai
        this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
        this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
    }

//    protected Image carregarImagem(String imagem) {
//        return new ImageIcon(Objects.requireNonNull(
//                getClass().getClassLoader().getResource("./"+imagem+".png")
//        )).getImage();
//    }

    //metodos abastractos
    public abstract void desenhar(Graphics g, JPanel painel);

    //metodo de acceso
    public int getVida() {return vida;}
    public int getAtaque() {return ataque;}
    public double getVelocidade() {return velocidade;}
    public Image getIcone() {return icone;}
}
