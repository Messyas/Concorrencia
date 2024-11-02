package ZZZ_ListaDeExercicios2.Ex01;

public class FuncionarioConsumidor implements Runnable {
    Buffer buffer;

    FuncionarioConsumidor (Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consumir();
                Thread.sleep(3000); //simula o tempo de desocupar uma sala
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
