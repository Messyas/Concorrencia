package ZZ_ListaDeExercicios.Ex04;

public class SimulacaoBancaria {
    public static void main(String[] args) throws InterruptedException {
        int numeroDeContas = 10;
        Conta[] contas = new Conta[numeroDeContas];

        // Inicializa as contas com saldo inicial de 1000
        for (int i = 0; i < numeroDeContas; i++) {
            contas[i] = new Conta(1000);
        }

        // Cria um conjunto de threads que fazem transferências aleatórias
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Transferencia(contas));
            threads[i].start();
        }

        // Espera todas as threads terminarem
        for (Thread t : threads) {
            t.join();
        }


        int saldoTotal = 0;
        for (Conta conta : contas) {
            saldoTotal += conta.getSaldo();
        }

        System.out.println("Saldo total: " + saldoTotal);
    }
}
