package ZZZ_ListaDeExercicio2.Ex03;

/**
 * Exercício 3: Estacionamento com Vagas Limitadas
 * Implemente um sistema para controlar o uso de um estacionamento com um número limitado de vagas. Quando o estacionamento estiver cheio,
 * os carros (threads) devem aguardar até que uma vaga seja liberada.
 * : Gerenciar o número de vagas disponíveis, permitindo que carros entrem ou saiam do estacionamento de forma sincronizada.
 * : Utilize a implementação de buffer para representar as vagas do estacionamento.
 *
 */

public class SistemaEstacionamento {
    public static void main(String[] args) {
        VagasBuffer vagasBuffer = new VagasBuffer();

        Thread filaDeEntrada = new Thread( new CarroProducer(vagasBuffer));
        Thread filaDeEntrada2 = new Thread( new CarroProducer(vagasBuffer));
        Thread filaDeSaida = new Thread( new EstacionamentoConsumer(vagasBuffer));

        filaDeEntrada.start();
        filaDeEntrada2.start();
        filaDeSaida.start();
    }
}
