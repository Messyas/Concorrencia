package ZZZ_ListaDeExercicios2.Ex03;

public class EstacionamentoConsumer implements Runnable{
    VagasBuffer vagasBuffer;

    EstacionamentoConsumer (VagasBuffer vagasBuffer) {
        this.vagasBuffer = vagasBuffer;
    }

    @Override
    public void run() {
        try{
            while (true) {
                vagasBuffer.consumir();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
