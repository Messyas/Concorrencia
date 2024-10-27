package ZZ_ListaDeExercicios.Ex03;

import java.util.LinkedList;

/**
 * Concorrencia
 * ------------------------------------------------------------------------------
 * Simule o problema do : uma barbearia tem um barbeiro, uma cadeira de barbeiro,
 * e uma sala de espera com três cadeiras. Se não há clientes, o barbeiro dorme
 * na cadeira dele. Quando um cliente chega:
 * Se o barbeiro está dormindo, ele é acordado para atender o cliente.
 * Se o barbeiro está ocupado e há cadeiras na sala de espera, o cliente aguarda.
 * Se todas as cadeiras estão ocupadas, o cliente vai embora.
 *-------------------------------------------------------------------------------
 * Implemente esse problema usando threads para simular clientes e o barbeiro, e
 * sincronize o acesso às cadeiras.: Usar threads para modelar uma situação de
 * espera com controle de acesso.
 * ------------------------------------------------------------------------------
 */

public class Barbearia {
    private final LinkedList<Cliente> salaEspera;
    private final int capacidadeSalaEspera;
    private boolean barbeiroOcupado = false;

    public Barbearia(int capacidadeSalaEspera) {
        this.capacidadeSalaEspera = capacidadeSalaEspera;
        this.salaEspera = new LinkedList<>();
    }


    public synchronized void chegarNaBarbearia(Cliente cliente) throws InterruptedException {
        System.out.println(cliente.getNome() + " entrou na barbearia. \n");

        if (!barbeiroOcupado && salaEspera.isEmpty()) {
            // o barbeiro não está ocupado e não há clientes esperando, atende diretamente
            System.out.println(cliente.getNome() + " acordou o barbeiro.");
            barbeiroOcupado = true;
            atenderCliente(cliente);
        } else if (salaEspera.size() < capacidadeSalaEspera) {
            // o barbeiro está ocupado, mas há espaço na sala de espera
            salaEspera.add(cliente);
            System.out.println(cliente.getNome() + " está esperando na sala de espera.");
        } else {
            // a sala de espera está cheia, o cliente vai embora
            System.out.println(cliente.getNome() + " foi embora, sala de espera cheia.");
        }
    }


    public synchronized void atenderCliente(Cliente cliente) throws InterruptedException {
        System.out.println("\n Barbeiro está atendendo " + cliente.getNome() + ".");
        Thread.sleep(2000); // Simulando o tempo de atendimento
        System.out.println("\n Barbeiro terminou de atender " + cliente.getNome() + ".");
        barbeiroOcupado = false;

        // Apos o atendimento, verifica se ha mais clientes esperando
        if (!salaEspera.isEmpty()) {
            Cliente proximoCliente = salaEspera.poll(); // Pega o próximo cliente
            barbeiroOcupado = true;
            atenderCliente(proximoCliente);
        } else {
            System.out.println("Barbeiro está dormindo.\n");
        }
    }
}
