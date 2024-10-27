package ZZ_ListaDeExercicios.Ex05;

import java.util.Random;

/**
 * ---------------------------------------------------------------------------------------------------------------------------------------------
 * Implemente o problema dos filósofos usando threads: cinco filósofos estão sentados ao redor de uma mesa, e cada um tem um prato de espaguete.
 * Eles têm dois garfos ao seu lado e precisam dos dois para comer. Após comer, eles devem pensar por um tempo.
 * Utilize sincronização para gerenciar o acesso aos garfos, de forma que o programa evite deadlocks.
 * : Aprender sobre deadlocks e como evitá-los ao utilizar sincronização em Java.
 * ---------------------------------------------------------------------------------------------------------------------------------------------
 */
class Filosofo implements Runnable {
    private final int idFilosofo;
    private final Mesa mesa;
    private final Random random = new Random();

    public Filosofo(int id, Mesa mesa) {
        this.idFilosofo = id;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + idFilosofo + " está pensando...");
        Thread.sleep(random.nextInt(5000)); // Filósofo pensa por um tempo aleatório
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + idFilosofo + " está com fome e tenta pegar os garfos...");

        mesa.pegarGarfos(idFilosofo); // Pega os garfos
        System.out.println("Filósofo " + idFilosofo + " está comendo...");

        Thread.sleep(random.nextInt(5000)); // Filósofo come por um tempo aleatório

        mesa.soltarGarfos(idFilosofo); // Solta os garfos
        System.out.println("Filósofo " + idFilosofo + " soltou os garfos e voltou a pensar.");
    }
}
//fonte: https://blog.pantuza.com/artigos/o-jantar-dos-filosofos-problema-de-sincronizacao-em-sistemas-operacionais