package ifsc.joe.domain.impl;
import ifsc.joe.enums.Recursos;
import java.awt.*;
import java.util.HashMap;

import static ifsc.joe.domain.impl.RecursoManager.carregarCache;
import static ifsc.joe.domain.impl.RecursoManager.getImagem;


public class RecursoFactory {
    private Image icone;
    private Recursos tipo;
    private HashMap<String,Integer> coordenadas;

    public RecursoFactory(Recursos tipo,int x, int y){
        this.coordenadas = new HashMap<>();
        coordenadas.put("HORIZONTAL",x);
        coordenadas.put("VERTICAL",y);
        this.tipo = tipo;
        String nomeImagem = tipo.toString().toLowerCase();
        carregarCache(nomeImagem);
        this.icone = getImagem(nomeImagem);
    }


}
