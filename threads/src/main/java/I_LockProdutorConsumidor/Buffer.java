package I_LockProdutorConsumidor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Buffer {
    private Queue<Integer> fila = new LinkedList<>();
    private final int LIMITE = 5;  // Limite do tamanho da fila
    private final Lock lock = new ReentrantLock();  // Lock para controlar o acesso ao buffer
    private final Condition bufferCheio = lock.newCondition();  // Condição para buffer cheio
    private final Condition bufferVazio = lock.newCondition();  // Condição para buffer vazio

    // Método para o produtor adicionar itens no buffer
    public void produzir(int valor) throws InterruptedException {
        lock.lock(); //quando se usa a palavra reservada lock nao ha necessidade de se utilzar a sincronizador, pois a classe Condition fara a sincronizacao
        try {
            // Enquanto o buffer estiver cheio, o produtor deve esperar
            while (fila.size() == LIMITE) {
                System.out.println("Buffer cheio. Produtor aguardando...");
                bufferCheio.await();  // Produtor aguarda até que o buffer tenha espaço
            }
            // Adiciona o item ao buffer
            fila.add(valor);
            System.out.println("Produtor produziu: " + valor);
            // Notifica o consumidor de que há um item disponível
            bufferVazio.signalAll(); // equivalente ao notifyall das abordagens anteriores
        } finally {
            lock.unlock();  // Libera o bloqueio
        }
    }

    // Método para o consumidor remover itens do buffer
    public int consumir() throws InterruptedException {
        lock.lock();
        try {
            // Enquanto o buffer estiver vazio, o consumidor deve esperar
            while (fila.isEmpty()) {
                System.out.println("Buffer vazio. Consumidor aguardando...");
                bufferVazio.await();  // Consumidor aguarda até que o buffer tenha itens
            }
            // Remove o item do buffer
            int valor = fila.poll();
            System.out.println("Consumidor consumiu: " + valor);
            // Notifica o produtor de que há espaço disponível no buffer
            bufferCheio.signalAll();
            return valor;
        } finally { // nao é obrigatorio, mas nesse caso apos a finalizacao dos blocos sera executado obrigatoriamente, nesse caso desbloqueando
            lock.unlock();  // Libera o bloqueio
        }
    }
}
