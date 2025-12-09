package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class ImageCache {
   public static HashMap<String, Image> imagens;

    public ImageCache() {
        imagens = new HashMap<>();
    }

    public static void carregarCache(String nomeImagem){
        Image imagem = new ImageIcon(Objects.requireNonNull(
            ImageCache.class.getClassLoader().getResource("./"+nomeImagem+".png")
        )).getImage();
        imagens.put(nomeImagem, imagem);
    }

    public static Image getImagem(String nomeImagem){
        return imagens.get(nomeImagem);
    }


}
