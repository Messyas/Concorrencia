package ZZZZ_ListaDeExercicios3.Ex1;

/**
 * Exercício 1: Exclusão Mútua com synchronized
 * Crie uma classe Contador que incrementa um contador compartilhado por várias threads.
 * Use synchronized para garantir que o contador seja atualizado corretamente.
 */
public class Contador implements Runnable {
    private int n = 1;

    public synchronized void contadorSincronizado() {
        System.out.println("Numero sincronizado: " + n++);
    }

    @Override
    public void run() {
        try {
            while (true) {
                contadorSincronizado();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Contador contador = new Contador();
        Thread threadContadora1 = new Thread(contador);
        Thread threadContadora2 = new Thread(contador);
        Thread threadContadora3 = new Thread(contador);

        threadContadora1.start();
        threadContadora2.start();
        threadContadora3.start();
    }
}
