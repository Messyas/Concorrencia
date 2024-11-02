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
    private final int limite;

    public ContadorReentrantLock(int limite) {
        this.limite = limite;
    }

    public void incrementarContador() {
        lock.lock(); // Tranca
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
                incrementarContador(); //chama a propria funcao recursiva
            }
        } finally {
            lock.unlock(); //desbloqueia o lock
        }
    }

    public static void main(String[] args) {
        ContadorReentrantLock contador = new ContadorReentrantLock(10);

        Thread thread = new Thread(contador::incrementarContador);
        Thread thread2 = new Thread(contador::incrementarContador);
        Thread thread3 = new Thread(contador::incrementarContador);
        thread.start();
        thread2.start();
        thread3.start();
    }
}
