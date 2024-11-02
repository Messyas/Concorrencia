package ZZZ_ListaDeExercicios2.Ex01;

public class SalaDeReuniaoProdutor implements Runnable{
    Buffer buffer;

    SalaDeReuniaoProdutor (Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        int valor = 1;
        try {
            while (true) {
                buffer.produzir(valor++);
                Thread.sleep(2000); // simula o tempo de introduzir uma nova sala ocupada
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
