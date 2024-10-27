package ZZZ_ListaDeExercicio2.Ex02;

public class ImpressoraConsumidor implements Runnable {
    Buffer buffer;

    ImpressoraConsumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consumidor();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
