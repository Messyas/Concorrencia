package ZZZ_ListaDeExercicios2.Ex02;

/**
 * Exercício 2: Sistema de Impressão Compartilhada
 * Vários computadores precisam enviar documentos para uma impressora compartilhada, mas a impressora só pode processar uma tarefa por vez.
 * Cada computador tenta adicionar uma tarefa de impressão na fila, e a impressora processa essas tarefas sequencialmente.
 * : Sincronizar o acesso à impressora compartilhada, garantindo que apenas uma thread possa imprimir de cada vez,
 * e usar um buffer para controlar o envio de documentos.
 * : Implemente uma fila limitada de tarefas de impressão, onde a impressora (consumidor) remove as tarefas e os computadores (produtores) adicionam.
 */

public class SistemaDeImpressao {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread computador1 = new Thread( new ComputadorProdutor(buffer));
        Thread computador2 = new Thread( new ComputadorProdutor(buffer));
        Thread computador3 = new Thread( new ComputadorProdutor(buffer));
        // Acho que precisa alterar alguma coisa pra garantir que cada computador tenha um numero diferente

        Thread impressora = new Thread( new ImpressoraConsumidor(buffer));

        impressora.start();

        computador1.start();
        computador2.start();
        computador3.start();
    }
}
