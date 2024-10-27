package G_Produtor_Consumidor_Buffer_SemSincronizacao;

//Produtor com um método run que insere os valores de 1 a 10 em buffer.
import java.security.SecureRandom;

public class Producer implements Runnable{
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation; // referência a objeto compartilhado

    // construtor
    public Producer(Buffer sharedLocation){
        this.sharedLocation = sharedLocation;
    }

    // armazena os valores de 1 a 10 em sharedLocationÿ
    public void run() {
        int sum = 0;

        for (int count = 1; count <= 10; count++){
            try { // dorme de 0 a 3 segundos, então coloca valor em Buffer

                Thread.sleep(generator.nextInt(3000)); // sono aleatório ÿ
                sharedLocation.blockingPut(count); // configura valor no bufferÿ
                sum += count; // incrementa soma de valores
                System.out.printf("\t%2d%n", sum);
            }catch (InterruptedException exception){
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Producer done producing%nTerminating Producer%n");
    }
} // fim da classe Producer
