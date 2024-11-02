package ZZZZ_ListaDeExercicios3.Ex2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Exercício 2: Mutex com ReentrantLock
 * Modifique o exercício anterior para usar um ReentrantLock em
 * vez de synchronized para proteger o incremento do contador.
 */

// Notas: Reentrantlock é uma classe que fornece uma maneira diferente de se usar threads com sincronizacao, o termo reentrant se refere a thread que ja possui o lock (bloqueio) e pode adiquiri-lo novamente sem problemas.
    //Isso permite que a thread acesse um recurso protegido sem causar deadlock (bloqueio eterno ao recurso).
public class ContadorReentrantLock {
    private int contador = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final int limite; // Limite para a recursão

    // Construtor que define o limite do contador
    public ContadorReentrantLock(int limite) {
        this.limite = limite;
    }

    public void incrementarContador() {
        lock.lock(); // Adquire o lock
        try {
            if (contador < limite) {
                contador++;
                System.out.println("Contador: " + contador);

                // Pausa para simular a espera entre incrementos
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Chama o método recursivamente
                incrementarContador();
            }
        } finally {
            lock.unlock(); // Libera o lock
        }
    }

    public static void main(String[] args) {
        // Define o limite como 10
        ContadorReentrantLock contador = new ContadorReentrantLock(10);

        // Executa o incremento de forma recursiva em uma thread separada
        Thread thread = new Thread(contador::incrementarContador);
        Thread thread2 = new Thread(contador::incrementarContador);
        Thread thread3 = new Thread(contador::incrementarContador);
        thread.start();
        thread2.start();
        thread3.start();
    }
}
