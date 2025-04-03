package ZZZZ_ListaDeExercicios3.Ex4;

import java.util.concurrent.Semaphore;

/**
 * Exercício 4: Semáforo para Controle de Quantidade Máxima de Conexões Simultâneas
 * Crie uma simulação de um servidor onde um número limitado de threads pode se conectar simultaneamente (máximo de 3 conexões permitidas).
 */

public class Servidor {
    private final int LIMITE = 3;
    private final Semaphore semaphore = new Semaphore(LIMITE, true);

    public void usarRecurso() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " esta usando uma instancia do servidor. ");

            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName() + " nao esta mais usando a instancia do servidor. ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
