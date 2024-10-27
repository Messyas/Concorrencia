package ZZ_ListaDeExercicios.Ex10;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simule um jogo de corrida onde cinco carros (representados por threads) competem em uma pista.
 * Cada thread deve mover o carro a uma velocidade aleatória e atualizar sua posição na corrida.
 * Sincronize o acesso a uma variável de posição para evitar inconsistências.
 * : Utilizar threads e sincronização em um cenário de simulação de jogo.
 */
public class JogoDeCorrida implements Runnable{
    private static final int DISTANCIA_TOTAL = 100; // Distância para a corrida
    private static final Lock lock = new ReentrantLock(); // Usando Lock para sincronizar o acesso
    private static int colocacao = 1; // Para registrar a posição final dos carros
    private final String nomeCarro;
    private final Random random = new Random();
    private int distanciaPercorrida = 0; // Distância que o carro já percorreu

    public JogoDeCorrida(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }

    @Override
    public void run() {
        while (distanciaPercorrida < DISTANCIA_TOTAL) {
            // Simula a velocidade aleatória do carro
            int velocidade = random.nextInt(10) + 1; // Velocidade entre 1 e 10
            distanciaPercorrida += velocidade;

            // Sincroniza o acesso à posição para exibir de forma justa
            lock.lock();
            try {
                System.out.println("----------------------------------------------------------------");
                System.out.println(nomeCarro + " percorreu " + distanciaPercorrida + " metros.");
                System.out.println("----------------------------------------------------------------");
                System.out.println("\n");
                if (distanciaPercorrida >= DISTANCIA_TOTAL) {
                    System.out.println(nomeCarro + " chegou em " + colocacao + "º lugar!");
                    colocacao++;
                }
            } finally {
                lock.unlock();
            }

            // Pausa para simular o tempo de "movimento" do carro
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Garante que a interrupção seja respeitada
            }
        }
    }

    public static void main(String[] args) {

        Thread carro1 = new Thread(new JogoDeCorrida("Carro 1"));
        Thread carro2 = new Thread(new JogoDeCorrida("Carro 2"));
        Thread carro3 = new Thread(new JogoDeCorrida("Carro 3"));
        Thread carro4 = new Thread(new JogoDeCorrida("Carro 4"));
        Thread carro5 = new Thread(new JogoDeCorrida("Carro 5"));

        // Inicia as threads (corrida começa)
        carro1.start();
        carro2.start();
        carro3.start();
        carro4.start();
        carro5.start();
    }
}
