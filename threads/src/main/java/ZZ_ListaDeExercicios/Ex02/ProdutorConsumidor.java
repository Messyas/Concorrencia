package ZZ_ListaDeExercicios.Ex02;


import java.util.LinkedList;

/**
 *
 * <Concorrencia>
 * implemente uma versão básica do padrão Produtor-Consumidor usando
 * uma LinkedList para armazenar dados. O produtor deve adicionar números inteiros
 * de 1 a 100 na fila. O consumidor deve remover e exibir esses números.
 * Limite o tamanho da fila para no máximo 10 elementos e implemente
 * sincronização para garantir que o produtor espere quando a fila estiver
 * cheia, e o consumidor espere quando a fila estiver vazia:
 * Praticar o uso de wait() e notify() para gerenciar uma fila limitada.
 */

public class ProdutorConsumidor {
    private final LinkedList<Integer> linkedList = new LinkedList<>();
    private final int CAPACIDADE = 10;

    private void produtor() throws InterruptedException {
        int valor = 1;
        while (valor <= 100) {
            synchronized (this) {
                while(linkedList.size() == CAPACIDADE) {
                    wait();
                }

                //add element
                System.out.printf("\nProdutor adicionou: " + valor);
                linkedList.add(valor++);

                notifyAll();
            }
        }
    }

    private void consumidor() throws InterruptedException {
        while (true) {
            synchronized (this) {

            while (linkedList.isEmpty()) {
                wait();
            }

            int valorConsumido = linkedList.removeFirst();
            System.out.printf("\nConsumidor removeu: " + valorConsumido);

            notifyAll();

            if (valorConsumido == 100) break;
          }
        }
    }

    public static void main(String[] args) {
        ProdutorConsumidor pc = new ProdutorConsumidor();
        Thread produtorThread = new Thread(() -> {
            try {
                pc.produtor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumidorThread = new Thread(() -> {
            try {
                pc.consumidor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        produtorThread.start();
        consumidorThread.start();
    }
}

///No caso desse codigo, a estrutura de dados é manipulada dentro do contexto da mesma classe, a constante limita a quantidade de
///incrementos na lista, o metodo produtor espera ate que o valor da constante seja atingido, e o consumidor espera ate que a lista
///nao esteja vazia, apos o incremento na lista o metodo notifyAll() permite que as threads que estavam esperando continuem os processos
///ate que as respectivas condicoes sejam atendidas, no caso ate que o produtor insira 100 inteiros na lista, e ate que o numero seja igual a 100 na lista.





