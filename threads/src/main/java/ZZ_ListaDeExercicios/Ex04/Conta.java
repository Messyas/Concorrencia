package ZZ_ListaDeExercicios.Ex04;

/**
 *--------------------------------------------------------------------------------------#
 * Simule um sistema de contas bancárias onde diversas threads representam transações   #
 * entre contas. Implemente um metodo transferir que transfere uma quantia entre duas   #
 * contas, e sincronize o metodo para evitar condições de corrida. Crie um conjunto de  #
 * threads que fazem transferências aleatórias entre várias contas e verifique se o     #
 * saldo total entre as contas permanece constante.                                     #
 * *************************************************************************************#
 *:Entender como a sincronização protege a integridade de dados em operações financeiras#
 *--------------------------------------------------------------------------------------#
 */

public class Conta {
    private int saldo;

    public Conta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    public synchronized void depositar(int quantia) {
        saldo += quantia;
    }

    public synchronized void sacar(int quantia) {
        saldo -= quantia;
    }

    public static void transferir(Conta de, Conta para, int quantia) {
        synchronized (de) { // Bloqueia a conta de origem
            synchronized (para) { // Bloqueia a conta de destino
                if (de.getSaldo() >= quantia) {
                    de.sacar(quantia);
                    para.depositar(quantia);
                    System.out.println(Thread.currentThread().getName() + " transferiu " + quantia + " de conta " + de + " para conta " + para);
                }
            }
        }
    }
}
