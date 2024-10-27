package D_PrintTask.C_SimuladorDeDownloads;

import javax.swing.*;
import java.awt.*;

public class DownloadTask implements Runnable {
    private String fileName;
    private JProgressBar progressBar;
    private JFrame frame;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
        setupUI(); // Configura a interface gráfica para cada download
    }

    private void setupUI() {
        frame = new JFrame("Download - " + fileName);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        frame.add(new JLabel("Baixando " + fileName), BorderLayout.NORTH);
        frame.add(progressBar, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    @Override
    public void run() {
        System.out.println("Iniciando o download de " + fileName);

        int totalSteps = 100; // Define o total em 100 etapas para corresponder à barra de progresso
        for (int i = 1; i <= totalSteps; i++) {
            try {
                Thread.sleep(800); // Simula o tempo de cada etapa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Atualiza a barra de progresso
            // Cria uma variável final para armazenar o valor de i
            final int progress = i;
            SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
        }

        System.out.println("Download de " + fileName + " concluído.");
        JOptionPane.showMessageDialog(frame, "Download de " + fileName + " concluído!", "Concluído", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose(); // Fecha a janela ao concluir o download
    }
}
