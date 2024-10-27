package D_PrintTask.C_SimuladorDeDownloads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulaDownloadSwing {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // Define o executor para controlar as threads

        executor.submit(new DownloadTask("arquivo1.zip"));
        executor.submit(new DownloadTask("arquivo2.zip"));
        executor.submit(new DownloadTask("arquivo3.zip"));

        executor.shutdown(); // Finaliza o executor após a execução das tarefas
    }
}