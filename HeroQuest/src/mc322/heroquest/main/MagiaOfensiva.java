package mc322.heroquest.main;

import java.util.*;

public abstract class MagiaOfensiva extends Magia {

    public MagiaOfensiva() {
    }
    
    protected int dano;

    protected abstract void lancarMagia(Combativel origem, Combativel alvo);

}