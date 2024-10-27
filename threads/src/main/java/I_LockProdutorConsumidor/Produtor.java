package I_LockProdutorConsumidor;

class Produtor implements Runnable {
    private Buffer buffer;

    public Produtor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int valor = 0;
        try {
            while (true) {
                buffer.produzir(valor++);
                Thread.sleep(500);  // Simula o tempo de produção
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
