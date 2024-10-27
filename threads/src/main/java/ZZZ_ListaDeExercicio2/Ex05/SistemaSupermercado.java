package ZZZ_ListaDeExercicio2.Ex05;

/**
 * * Exercício 5: Simulação de Caixas em um Supermercado
 *  * Implemente um sistema que simula o atendimento em caixas de supermercado. Existem 5 caixas disponíveis,
 *  * e os clientes (representados por threads) entram na fila para serem atendidos. Quando um caixa está livre,
 *  * o próximo cliente é atendido. Se todos os caixas estiverem ocupados, os clientes devem aguardar na fila.
 *  * : Sincronizar o acesso aos caixas, permitindo que os clientes sejam atendidos à medida que os caixas são liberados.
 *  * : Use um para representar os caixas disponíveis e implemente o padrão Produtor-Consumidor,
 *  * onde os clientes são os produtores que entram na fila, e os caixas são os consumidores que os atendem.
 */

public class SistemaSupermercado {
    public static void main(String[] args) {
        FilaBuffer filaBuffer = new FilaBuffer();

        Thread clientes1 = new Thread(new ClienteProdutor(filaBuffer));
        Thread clientes11 = new Thread(new ClienteProdutor(filaBuffer));
        Thread caixa1 = new Thread(new CaixaConsumidor(filaBuffer));

        Thread clientes2 = new Thread(new ClienteProdutor(filaBuffer));
        Thread caixa2 = new Thread(new CaixaConsumidor(filaBuffer));

        clientes1.start();
        clientes11.start();
        caixa1.start();
        clientes2.start();
        caixa2.start();

    }
}
