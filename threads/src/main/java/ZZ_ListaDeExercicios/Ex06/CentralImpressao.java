package ZZ_ListaDeExercicios.Ex06;

class CentralImpressao {
    private final int limiteImpressoes; // Limite de impressões antes da manutenção
    private int impressoesRealizadas = 0; // Contador de impressões realizadas
    private boolean emManutencao = false; // Estado da impressora (se está em manutenção)

    public CentralImpressao(int limiteImpressoes) {
        this.limiteImpressoes = limiteImpressoes;
    }


    public synchronized void imprimir(String documento) throws InterruptedException {
        // Se a impressora está em manutenção ou atingiu o limite, espera a manutenção ser concluída
        while (impressoesRealizadas >= limiteImpressoes || emManutencao) {
            System.out.println(Thread.currentThread().getName() + " aguardando manutenção.");
            wait(); // Espera até que a manutenção seja realizada
        }


        System.out.println(Thread.currentThread().getName() + " está imprimindo: " + documento);
        impressoesRealizadas++;
        System.out.println("Impressões realizadas: " + impressoesRealizadas + "/" + limiteImpressoes);

        // Se atingiu o limite, sinaliza que a impressora precisa de manutenção
        if (impressoesRealizadas >= limiteImpressoes) {
            emManutencao = true;
            System.out.println("Impressora atingiu o limite. Necessário manutenção.");
        }
    }


    public synchronized void realizarManutencao() throws InterruptedException {
        if (!emManutencao) {
            System.out.println("Nenhuma manutenção necessária.");
            return;
        }

        // Simulando o tempo de manutenção
        System.out.println("Técnico realizando manutenção...");
        Thread.sleep(2000); // Simula o tempo gasto na manutenção
        impressoesRealizadas = 0; // Reseta o contador de impressões
        emManutencao = false; // Impressora está disponível novamente
        System.out.println("Manutenção concluída. Impressora está pronta.");

        // Notifica todas as threads que estavam esperando
        notifyAll();
    }
}
