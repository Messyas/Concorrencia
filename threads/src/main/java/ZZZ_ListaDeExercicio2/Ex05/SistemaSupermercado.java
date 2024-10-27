package ZZZ_ListaDeExercicio2.Ex05;

/**
 * * Exercício 5: Simulação de Caixas em um Supermercado
 *  * Implemente um sistema que simula o atendimento em caixas de supermercado. Existem 5 caixas disponíveis,
 *  * e os clientes (representados por threads) entram na fila para serem atendidos. Quando um caixa está livre,
 *  * o próximo cliente é atendido. Se todos os caixas estiverem ocupados, os clientes devem aguardar na fila.
 *  * : Sincronizar o acesso aos caixas, permitindo que os clientes sejam atendidos à medida que os caixas são liberados.
 *  * : Use um para representar os caixas disponíveis e implemente o padrão Produtor-Consumidor,
 *  * onde os clientes são os produtores que entram na fila, e os caixas são os consumidores que os atendem.
 *  * Instruções para Implementação:: Use um buffer que representa as salas de reunião disponíveis,
 *  * com métodos sincronizados para reservar e liberar uma sala.: Implemente um buffer para a fila de impressão e use notifyAll()
 *  * para liberar a impressora quando uma tarefa é concluída.: Aplique o conceito de buffer limitado, onde cada vaga de
 *  * estacionamento é representada por uma posição no buffer.: Utilize Lock e Condition para gerenciar o número máximo
 *  * de conexões simultâneas ao banco de dados.: Controle o número de caixas usando um buffer limitado e permita
 *  * que os clientes aguardem até que um caixa esteja disponível.
 */

public class SistemaSupermercado {
    public static void main(String[] args) {

    }
}
