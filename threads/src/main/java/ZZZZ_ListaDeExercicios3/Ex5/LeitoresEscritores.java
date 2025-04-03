package ZZZZ_ListaDeExercicios3.Ex5;

/**
 * Exercício 5: Leitores e Escritores com ReentrantReadWriteLock
 * Implemente um sistema de leitura/escrita onde várias threads podem ler ao mesmo tempo, mas apenas uma thread pode escrever de cada vez.
 */
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LeitoresEscritores {
    private int dadoCompartilhado = 0; // Recurso compartilhado que será lido/escrito
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    // Método para leitura do dado compartilhado
    public int lerDado() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " está lendo o dado: " + dadoCompartilhado);
            Thread.sleep(1000); // Simula o tempo de leitura
            return dadoCompartilhado;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
            System.out.println(Thread.currentThread().getName() + " terminou de ler o dado.");
            readLock.unlock();
        }
    }

    // Método para escrita no dado compartilhado
    public void escreverDado(int novoDado) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " está escrevendo o dado: " + novoDado);
            Thread.sleep(1000); // Simula o tempo de escrita
            dadoCompartilhado = novoDado;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " terminou de escrever o dado.");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        LeitoresEscritores recurso = new LeitoresEscritores();

        // Criação de threads de leitura
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                recurso.lerDado();
            }, "Leitor-" + i).start();
        }

        // Criação de threads de escrita
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                recurso.escreverDado((int) (Math.random() * 100));
            }, "Escritor-" + i).start();
        }
    }
}
