package F_ExemploCerto;

//Adiciona inteiros a um array compartilhado com outros Runnables

import java.lang.Runnable;

public class ArrayWriter implements Runnable{
    private final SimpleArraySincronizado simpleArraySincronizado;
    private final int startValue;

    public ArrayWriter(int value, SimpleArraySincronizado array){
        startValue = value;
        simpleArraySincronizado = array;
    }

    public void run(){
        for (int i = startValue; i < startValue + 3; i++){
            simpleArraySincronizado.add(i); // adiciona um elemento ao array compartilhado
        }
    }
} // fim da classe ArrayWriter

//tem que arrumar a sincronizacao disso ai