package ZZZ_ListaDeExercicio2.Ex04;

/**
 * Exercício 4: Controle de Acesso a um Banco de Dados
 * Implemente um sistema de controle de acesso para uma base de dados onde várias threads (clientes) tentam acessar o banco.
 * Apenas um número limitado de conexões ao banco pode ser mantido simultaneamente. Se todas as conexões estiverem em uso,
 * novas threads devem aguardar até que uma conexão seja liberada.
 * : Sincronizar o acesso ao banco de dados, garantindo que apenas um número fixo de conexões esteja ativo de cada vez.
 * : Use Lock e Condition para implementar o controle das conexões e gerenciar a espera de novas threads.
 */

public class SistemaBancoDeDados {
    public static void main(String[] args) {
        FilaBancoBuffer filaBancoBuffer = new FilaBancoBuffer();

        Thread filaAcesso = new Thread(new FilaBancoBuffer(filaBancoBuffer));

        Thread cliente1 = new Thread(new ClienteBD(filaBancoBuffer));
        Thread cliente2 = new Thread(new ClienteBD(filaBancoBuffer));
        Thread cliente3 = new Thread(new ClienteBD(filaBancoBuffer));

        filaAcesso.start();

        cliente1.start();
        cliente2.start();
        cliente3.start();
    }
}
