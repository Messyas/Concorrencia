package ZZZ_ListaDeExercicio2.Ex05;

import java.util.LinkedList;

public class FilaBuffer {
    private LinkedList <Integer> filaDoCaixa = new LinkedList<>();
    private final int LIMITE = 5;

    public synchronized void produzir(int valor) throws InterruptedException {
        while(filaDoCaixa.size() == LIMITE) {
            wait();
            System.out.println("Fila cheia, espere para entrar! \n");
        }
        filaDoCaixa.add(valor);
        System.out.println("O cliente numero " + valor + " entrou na fila. \n");
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException{
        while(filaDoCaixa.isEmpty()) {
            wait();
            System.out.println("Fila vazia, Esperando clientes entrarem. \n");
        }
        int valor = filaDoCaixa.poll();
        System.out.println("O cliente " + valor + " Saiu da fila. \n");
        notifyAll();
        return valor;
    }
}
