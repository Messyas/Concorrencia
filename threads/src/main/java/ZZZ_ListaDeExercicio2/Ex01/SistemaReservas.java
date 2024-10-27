package ZZZ_ListaDeExercicio2.Ex01;

/**
 * Exercício 1: Sistema de Reservas de Salas de Reunião
 * Crie um sistema de gerenciamento de reservas de salas de reunião em uma empresa.
 * Existem 3 salas disponíveis e vários funcionários (representados por threads) tentando reservar uma sala.
 * Quando todas as salas estão ocupadas, os funcionários devem aguardar até que uma sala seja liberada.
 * : Implementar o controle de reservas e sincronizar o acesso às salas utilizando synchronized, wait(), e notifyAll().
 * : Utilize um buffer que represente o número de salas disponíveis e implemente o padrão Produtor-Consumidor.
 */

public class SistemaReservas {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread funcionario1 = new Thread(new SalaDeReuniaoProdutor(buffer));
        Thread funcionario2 = new Thread(new FuncionarioConsumidor(buffer));

        funcionario1.start();
        funcionario2.start();

    }
}
