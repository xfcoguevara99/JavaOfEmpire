package ifsc.joe.domain.impl;

import ifsc.joe.enums.TipoPersonagem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class RecursoManager {
   public static HashMap<String, Image> imagens;

    public RecursoManager() {
        imagens = new HashMap<>();
    }

    public static void carregarCache(String nomeImagem){
        Image imagem = new ImageIcon(Objects.requireNonNull(
            RecursoManager.class.getClassLoader().getResource("./"+nomeImagem+".png")
        )).getImage();
        imagens.put(nomeImagem, imagem);
    }

    public static Image getImagem(String nomeImagem){
        return imagens.get(nomeImagem);
    }


}
