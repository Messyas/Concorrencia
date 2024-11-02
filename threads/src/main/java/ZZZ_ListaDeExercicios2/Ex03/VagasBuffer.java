package ZZZ_ListaDeExercicios2.Ex03;

import java.util.LinkedList;

public class VagasBuffer {
    private LinkedList<Integer> vagasDisponiveis = new LinkedList<>();
    private final int LIMITE = 7;

    public synchronized void produzir(int valor) throws InterruptedException {
        while(vagasDisponiveis.size() == LIMITE) {
            System.out.println("Nao ha vagas disponiveis! ");
            wait();
        }
        vagasDisponiveis.add(valor);
        System.out.println("Vaga " + valor + " foi ocupada. \n");
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        while (vagasDisponiveis.isEmpty()) {

            wait();
        }
        int valor = vagasDisponiveis.poll();
        System.out.println("Vaga " + valor + " Disponivel. ");
        notifyAll();
        return valor;
    }
}
