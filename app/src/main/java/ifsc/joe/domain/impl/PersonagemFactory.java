package ifsc.joe.domain.impl;

import ifsc.joe.core.Personagem;
import ifsc.joe.enums.TipoPersonagem;

public class PersonagemFactory {
    public static Personagem criar(TipoPersonagem tipo, int x, int y){
        return switch (tipo) {
            case TipoPersonagem.ALDEAO -> new Aldeao(x, y);
            case TipoPersonagem.ARQUEIRO -> new Arqueiro(x, y);
            case TipoPersonagem.CAVALEIRO -> new Cavaleiro(x, y);
        };
    }

}
