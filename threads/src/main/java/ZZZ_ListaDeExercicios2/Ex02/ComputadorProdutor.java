package ZZZ_ListaDeExercicios2.Ex02;

public class ComputadorProdutor implements Runnable {
    Buffer buffer;

    ComputadorProdutor (Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int valor = 0;
        try{
            while (true) {
                buffer.produtor(valor++);
                if(valor > 5) {
                    System.out.println("Erro! Sobrecarga na fila de impressao. \n");
                    break;
                }
                Thread.sleep(4000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
