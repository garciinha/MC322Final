package mc322.heroquest.main;

import java.util.*;

public class Ouro extends Item {

    public Ouro() {
    	this.nome = "Moedas de Ouro";
    }

    private int valor;

    public static int[] valores;
    
    @Override
    public String getInformation(){
        return valor + " " + nome;
    }

}
