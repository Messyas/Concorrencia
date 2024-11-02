package ZZZ_ListaDeExercicios2.Ex01;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Queue<Integer> salasDisponiveis = new LinkedList<>();
    private final int LIMITE_DE_SALAS= 3;

    public synchronized void produzir (int valor) throws InterruptedException {
        while(salasDisponiveis.size() == LIMITE_DE_SALAS) {
            System.out.println("\nTodas as salas ocupadas.");
            wait();
        }
        salasDisponiveis.add(valor);
        System.out.println("\nUma sala foi ocupada: ");
        notifyAll(); //Informa que um item foi introduzido
    }

    public synchronized int consumir() throws InterruptedException {
        while (salasDisponiveis.isEmpty()) {
            System.out.println("\nTodas as salas estao vazias, aguardando ate serem ocupadas");
            wait();
        }
        int valor = salasDisponiveis.poll();
        System.out.println("Sala foi desocupada: ");
        notifyAll(); // Informa que a lista foi reduzida.
        return valor;
    }
}
