package ZZ_ListaDeExercicios.Ex09;

/**
 * Implemente uma classe Cofre que possui um metodo depositar e um metodo retirar, que controlam o saldo.
 * Crie várias threads que tentam realizar depósitos e retiradas aleatórias e sincronize o acesso para
 * garantir que o saldo não fique negativo e que os depósitos sejam feitos corretamente.
 * Adicione uma funcionalidade para que, caso o saldo caia abaixo de um determinado valor,
 * a thread aguarde um depósito antes de tentar uma nova retirada.
 * : Trabalhar com sincronização em um cenário onde várias threads acessam um recurso financeiro.
 */
public class Cofre {
    private double saldo; // Saldo do cofre
    private final Object lock = new Object(); // Objeto de sincronização

    public Cofre(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Método para depositar dinheiro no cofre
    public void depositar(double quantia) {
        synchronized (lock) {
            saldo += quantia;
            System.out.println(Thread.currentThread().getName() + " depositou " + quantia + ". Saldo atual: " + saldo);
            lock.notifyAll(); // Notifica todas as threads que estão aguardando
        }
    }

    // Método para retirar dinheiro do cofre
    public void retirar(double quantia) throws InterruptedException {
        synchronized (lock) {
            // Aguarda até que haja saldo suficiente para retirar
            while (saldo < quantia) {
                System.out.println(Thread.currentThread().getName() + " aguardando para retirar " + quantia + ". Saldo atual: " + saldo);
                lock.wait(); // A thread aguarda até que um depósito seja feito
            }
            saldo -= quantia;
            System.out.println(Thread.currentThread().getName() + " retirou " + quantia + ". Saldo atual: " + saldo);
        }
    }

    // Método para obter o saldo atual
    public double getSaldo() {
        synchronized (lock) {
            return saldo;
        }
    }

    // Teste da classe Cofre com várias threads realizando depósitos e retiradas
    public static void main(String[] args) {
        Cofre cofre = new Cofre(1000); // Saldo inicial

        // Thread de depósito
        Runnable depositador = () -> {
            for (int i = 0; i < 5; i++) {
                double quantia = Math.random() * 500; // Depósito aleatório
                cofre.depositar(quantia);
                try {
                    Thread.sleep(1000); // Simula o tempo entre os depósitos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Thread de retirada
        Runnable retirador = () -> {
            for (int i = 0; i < 5; i++) {
                double quantia = Math.random() * 1000; // Retirada aleatória
                try {
                    cofre.retirar(quantia);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                try {
                    Thread.sleep(1500); // Simula o tempo entre as retiradas
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Criando e iniciando várias threads de depósito e retirada
        Thread t1 = new Thread(depositador, "Depositador 1");
        Thread t2 = new Thread(depositador, "Depositador 2");
        Thread t3 = new Thread(retirador, "Retirador 1");
        Thread t4 = new Thread(retirador, "Retirador 2");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

