package I_LockProdutorConsumidor;

/**
 * Tanto o Lock quando o contition permitem ter mais contrele sobre o monitoramento da thread
 */
public class ExemploProdutorConsumidorLockCondition {
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