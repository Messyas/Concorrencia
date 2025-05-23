package I_LockProdutorConsumidor;

class Consumidor implements Runnable {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consumir();
                Thread.sleep(1000);  // Simula o tempo de consumo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}