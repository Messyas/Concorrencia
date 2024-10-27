package ZZZ_ListaDeExercicio2.Ex04;

public class ClienteBD implements Runnable {
    FilaBancoBuffer filaBancoBuffer;

    ClienteBD(FilaBancoBuffer filaBancoBuffer) {
        this.filaBancoBuffer = filaBancoBuffer;
    }

    @Override
    public void run() {
        try {
            while(true) {
                filaBancoBuffer.consumir();
                Thread.sleep(2900);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
