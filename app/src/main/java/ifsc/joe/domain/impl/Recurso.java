package ifsc.joe.domain.impl;
import ifsc.joe.enums.Recursos;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static ifsc.joe.domain.impl.ImageCache.*;


public class Recurso {
    private Image icone;
    private Recursos tipo;
    private HashMap<String,Integer> coordenadas;

    public Recurso(Recursos tipo, int x, int y){
        this.coordenadas = new HashMap<>();
        coordenadas.put("HORIZONTAL",x);
        coordenadas.put("VERTICAL",y);
        this.tipo = tipo;
        String nomeImagem = tipo.toString().toLowerCase();
        carregarCache(nomeImagem);
        this.icone = getImagem(nomeImagem);
    }

    public void desenhar(Graphics g, JPanel painel) {
        g.drawImage(this.icone, this.coordenadas.get("HORIZONTAL"), this.coordenadas.get("VERTICAL"), painel);
    }

    public Image getIcone() {
        return icone;
    }
    public Recursos getTipo() {
        return tipo;
    }
    public int getPosX(){
        return coordenadas.get("HORIZONTAL");
    }
    public int getPosY(){
        return coordenadas.get("VERTICAL");
    }
}
