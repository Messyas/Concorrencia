package ZZ_ListaDeExercicios.Ex06;

class Tecnico implements Runnable {
    private final CentralImpressao centralImpressao;

    public Tecnico(CentralImpressao centralImpressao) {
        this.centralImpressao = centralImpressao;
    }

    @Override
    public void run() {
        try {
            while (true) {
                centralImpressao.realizarManutencao();
                Thread.sleep(5000); // Intervalo entre verificações de manutenção
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}