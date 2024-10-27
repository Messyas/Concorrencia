package ZZZ_ListaDeExercicio2.Ex05;

public class CaixaConsumidor implements Runnable {
    private FilaBuffer filaBuffer;
    private volatile boolean running = true; //eu so queria usar o volatile mesmo, acho legal.

    CaixaConsumidor (FilaBuffer filaBuffer) {
        this.filaBuffer = filaBuffer;
    }

    @Override
    public void run() {
        try {
            while (running) {
                filaBuffer.consumir();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
