package ZZZZ_ListaDeExercicios3.Ex3;

import java.util.concurrent.Semaphore;

/**
 * Exercício 3: Controle de Acesso com Semaphore
 * Implemente uma classe RecursoCompartilhado onde apenas duas threads podem acessar o recurso ao mesmo tempo usando Semaphore.
 */

public class RecursoCompartilhado {
    private final Semaphore semaphore = new Semaphore(2, true);

    public void usarRecurso() {
        try {
            semaphore.acquire(); //concede uma permissao para uma thread
            System.out.println(Thread.currentThread().getName() + " esta usando o recurso. ");

            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName() + " liberou o recurso.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); //libera a permicao para outra thread
        }
    }

    public static void main(String[] args) {
        RecursoCompartilhado recurso = new RecursoCompartilhado();

        for (int i = 0; i <= 10; i++) {
            new Thread(recurso::usarRecurso).start();
        }
    }
}