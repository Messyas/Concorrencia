package ZZ_ListaDeExercicios.Ex01;

/**
 *
 * <Concorrencia>
 * Crie uma classe Contador que tenha um metodo incrementar() para incrementar um valor inteiro.
 * Crie duas threads que chamem o metodo incrementar() 1000 vezes cada uma.
 * Sincronize o metodo para garantir que o valor final seja o correto.
 * : Entender o uso de synchronized para métodos que manipulam dados compartilhados.
 */

public class Contador implements Runnable {
    private int valor = 0;


    public synchronized void incrementar() {
        valor++;
    }


    public int getValor() {
        return valor;
    }


    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            incrementar();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Contador contador = new Contador();

        Thread t1 = new Thread(contador);
        Thread t2 = new Thread(contador);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.printf("Valor Final: " + contador.getValor());
    }
}

/// Notas: Basicamente a sincronizacao cria uma fila que obriga que N processos sejam executados de forma assincrona, eliminando o fenomeno da corrida por memoria, impedindo sobrescrita indevida
