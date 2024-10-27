package ZZ_ListaDeExercicios.Ex07;

/**
 * Simule o funcionamento de um semáforo (sinal de trânsito) em uma estrada onde os carros de uma direção podem passar apenas quando o semáforo está verde.
 * Implemente duas threads: uma para simular o controle do semáforo, alternando entre verde e vermelho a cada intervalo de tempo; e outra para simular o
 * fluxo de carros que aguardam no sinal vermelho e passam quando o sinal está verde.
 * : Entender a sincronização para controlar threads com base em condições de estado.
 *
 */

public class Semaforo {
    private boolean verde = false; // Estado do semáforo, inicialmente vermelho

    // Método para trocar o estado do semáforo
    public synchronized void mudarParaVerde() {
        verde = true;
        System.out.println("Semáforo verde! Carros podem passar.");
        notifyAll(); // Notifica as threads que estão esperando
    }

    public synchronized void mudarParaVermelho() {
        verde = false;
        System.out.println("Semáforo vermelho! Carros devem parar.");
    }

    // Método para carros aguardarem no vermelho e passarem no verde
    public synchronized void aguardarSinalVerde() throws InterruptedException {
        while (!verde) {
            System.out.println(Thread.currentThread().getName() + " está esperando o sinal verde.");
            wait(); // Aguardando até o semáforo mudar para verde
        }
        System.out.println(Thread.currentThread().getName() + " passou pelo semáforo.");
    }

    // Thread para simular o controle do semáforo
    static class ControleSemaforo implements Runnable {
        private final Semaforo semaforo;

        public ControleSemaforo(Semaforo semaforo) {
            this.semaforo = semaforo;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(5000); // Intervalo de 5 segundos com o sinal verde
                    semaforo.mudarParaVerde();

                    Thread.sleep(5000); // Intervalo de 5 segundos com o sinal vermelho
                    semaforo.mudarParaVermelho();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Thread para simular os carros
    static class Carro implements Runnable {
        private final Semaforo semaforo;

        public Carro(Semaforo semaforo) {
            this.semaforo = semaforo;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    semaforo.aguardarSinalVerde(); // Carros aguardam o sinal verde para passar
                    Thread.sleep(1000); // Simula o tempo que o carro leva para passar
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Semaforo semaforo = new Semaforo();

        // Thread para controlar o semáforo
        Thread controleSemaforo = new Thread(new ControleSemaforo(semaforo), "ControleSemaforo");

        // Threads para simular carros
        Thread carro1 = new Thread(new Carro(semaforo), "Carro 1");
        Thread carro2 = new Thread(new Carro(semaforo), "Carro 2");
        Thread carro3 = new Thread(new Carro(semaforo), "Carro 3");

        // Inicia as threads
        controleSemaforo.start();
        carro1.start();
        carro2.start();
        carro3.start();
    }
}
