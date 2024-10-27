package ZZ_ListaDeExercicios.Ex05;

public class JantarDosFilosofos {
    public static void main(String[] args) {
        int numeroDeFilosofos = 5;
        Mesa mesa = new Mesa();
        Thread[] filosofos = new Thread[numeroDeFilosofos];

        // Inicializa e inicia as threads de filósofos
        for (int i = 0; i < numeroDeFilosofos; i++) {
            filosofos[i] = new Thread(new Filosofo(i, mesa));
            filosofos[i].start();
        }

        // Espera todas as threads (embora o programa seja infinito, para propósitos reais seria necessário algum controle)
        for (Thread filosofo : filosofos) {
            try {
                filosofo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}