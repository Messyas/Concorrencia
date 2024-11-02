package ZZZ_ListaDeExercicios2.Ex03;

public class CarroProducer implements Runnable{
    VagasBuffer vagasBuffer;

    CarroProducer (VagasBuffer vagasBuffer) {
        this.vagasBuffer = vagasBuffer;
    }

    @Override
    public void run() {
        int valor = 1;
        try {
            while (true) {
                vagasBuffer.produzir(valor++);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
