package ZZ_ListaDeExercicios.Ex06;

/**
 * Suponha que vários computadores precisam acessar uma central de impressão compartilhada.
 * Implemente uma classe CentralImpressao que permite que apenas uma thread (representando um computador) use a impressora por vez.
 * Adicione um contador que limita a quantidade de impressões que podem ser feitas antes que a impressora precise de manutenção.
 * Quando o limite for atingido, todas as threads devem aguardar até que um técnico (outra thread) realize a manutenção.
 * : Praticar o uso de wait() e notifyAll() para gerenciar acesso a um recurso limitado.
 */


class Computador implements Runnable {
    private final CentralImpressao centralImpressao;
    private final String documento;

    public Computador(CentralImpressao centralImpressao, String documento) {
        this.centralImpressao = centralImpressao;
        this.documento = documento;
    }

    @Override
    public void run() {
        try {
            while (true) {
                centralImpressao.imprimir(documento);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



