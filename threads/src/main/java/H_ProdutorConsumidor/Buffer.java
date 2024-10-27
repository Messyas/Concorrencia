package H_ProdutorConsumidor;

import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private Queue<Integer> fila = new LinkedList<>();
    private final int LIMITE = 5;  // Limite do tamanho da fila

    // Método para o produtor adicionar itens no buffer
    public synchronized void produzir(int valor) throws InterruptedException {
        // Enquanto o buffer estiver cheio, o produtor deve esperar
        while (fila.size() == LIMITE) {
            System.out.println("Buffer cheio. Produtor aguardando...");
            wait(); // O produtor espera
        }
        // Adiciona o item ao buffer
        fila.add(valor);
        System.out.println("Produtor produziu: " + valor);
        // Notifica o consumidor que um item foi produzido
        notifyAll();
    }

    // Método para o consumidor remover itens do buffer
    public synchronized int consumir() throws InterruptedException {
        // Enquanto o buffer estiver vazio, o consumidor deve esperar
        while (fila.isEmpty()) {
            System.out.println("Buffer vazio. Consumidor aguardando...");
            wait(); // O consumidor espera
        }
        // Remove o item do buffer
        int valor = fila.poll();
        System.out.println("Consumidor consumiu: " + valor);
        // Notifica o produtor que há espaço disponível no buffer
        notifyAll();
        return valor;
    }
}