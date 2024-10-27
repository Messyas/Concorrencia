package H_ProdutorConsumidor;

public class ExemploProdutorConsumidor {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        // Cria o produtor e o consumidor
        Thread produtor = new Thread(new Produtor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));

        // Inicia as threads
        produtor.start();
        consumidor.start();
    }
}
