package ZZZ_ListaDeExercicio2.Ex05;

public class ClienteProdutor implements Runnable {
    private FilaBuffer filaBuffer;

    ClienteProdutor (FilaBuffer filaBuffer) {
        this.filaBuffer = filaBuffer;
    }

    @Override
    public void run() {
        int valor = 1;
        try {
            while (true) {
                filaBuffer.produzir(valor++);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
