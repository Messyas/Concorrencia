package ZZZ_ListaDeExercicio2.Ex04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FilaBancoBuffer implements Runnable {
    private Queue<Integer> numeroConecoes = new LinkedList<>();
    private final int LIMITE = 3;  // Limite do tamanho da fila
    private final Lock lock = new ReentrantLock();  // Lock para controlar o acesso ao buffer
    private final Condition bufferCheio = lock.newCondition();  // Condição para buffer cheio
    private final Condition bufferVazio = lock.newCondition();  // Condição para buffer vazio
    private FilaBancoBuffer filaBancoBuffer;

    FilaBancoBuffer (FilaBancoBuffer filaBancoBuffer) {
        this.filaBancoBuffer = filaBancoBuffer;
    }

    public FilaBancoBuffer() {

    }

    public void produzir(int valor) throws InterruptedException {
        lock.lock();
        try {
            while(numeroConecoes.size() == LIMITE) {
                System.out.println("Numero de conexoes limite atingido. \n");
                bufferCheio.await();
            }
            numeroConecoes.add(valor);
            System.out.println("Conexao " + valor + "Estabelecida. ");
            bufferVazio.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int consumir() throws InterruptedException {
        lock.lock();
        try {
            while (numeroConecoes.isEmpty()) {
                System.out.println("Sem conecoes estabelecidadas. ");
                bufferVazio.await();
            }
            int valor = numeroConecoes.poll();
            System.out.println("A conecao" + valor + " foi estabelecida. ");
            bufferVazio.signalAll();

            return valor;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            int valor = 1;
            while (true) {
                filaBancoBuffer.produzir(valor++);
                Thread.sleep(2679);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
