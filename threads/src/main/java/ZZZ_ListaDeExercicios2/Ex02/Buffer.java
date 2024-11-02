package ZZZ_ListaDeExercicios2.Ex02;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Queue <Integer> filaDeImpressao = new LinkedList<>();
    private final int LIMITE_DE_IMPRESSAO = 5;

    public synchronized void produtor (int valor) throws InterruptedException {
        while(filaDeImpressao.size() == LIMITE_DE_IMPRESSAO) {
            System.out.println("------------------------------------------------------------");
            System.out.println("             Fila de impressao cheia!    ");
            System.out.println("------------------------------------------------------------");
            System.out.println("\n");
            wait();
        }
        filaDeImpressao.add(valor);
        System.out.println("------------------------------------------------------------");
        System.out.println("|- O documento " + valor + " adicionado a fila de impressao -|");
        System.out.println("------------------------------------------------------------");
        System.out.println("\n");
        notifyAll();
    }

    public synchronized int consumidor () throws InterruptedException {
        while (filaDeImpressao.isEmpty()) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("|- A fila de impressao esta vazia. Esperando ate receber documentos -|");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("\n");
            wait();
        }
        int valor = filaDeImpressao.poll();
        System.out.println("------------------------------------------------------------");
        System.out.println("--------   Imprimindo documento " + valor + "   ---------");
        System.out.println("------------------------------------------------------------");
        System.out.println("\n");
        notifyAll();

        return valor;
    }
}
