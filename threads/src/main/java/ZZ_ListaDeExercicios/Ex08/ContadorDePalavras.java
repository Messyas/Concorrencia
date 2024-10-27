package ZZ_ListaDeExercicios.Ex08;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Divida o trabalho de contagem de palavras em um arquivo grande em várias threads,
 * onde cada thread processa uma seção do arquivo e conta as palavras.
 * Utilize sincronização para somar os resultados de cada thread em um total final.
 * : Experimentar com o paralelismo de processamento e sincronizar a coleta de resultados parciais.
 */

public class ContadorDePalavras {
    private static int totalPalavras = 0; // Variavel para armazenar o total de palavras
    private static final Lock lock = new ReentrantLock(); // Lock para sincronizacao

    public static void main(String[] args) throws IOException {
        File arquivo = new File("D:\\Intelijei\\DAD\\threads\\src\\main\\java\\ZZ_ListaDeExercicios\\Ex08\\arquivo_grande.txt");
        int numeroDeThreads = 4; // Quantidade de threads
        long tamanhoArquivo = arquivo.length(); // retorna o tamanho do arquivo em bytes
        long bytesPorThread = tamanhoArquivo / numeroDeThreads; // Divide o arquivo em partes

        // Lista para armazenar as threads
        List<Thread> threads = new ArrayList<>();

        // Inicia uma nova instancia de RandomAccessFile para cada thread
        for (int i = 0; i < numeroDeThreads; i++) {
            long inicio = i * bytesPorThread;
            long fim = (i == numeroDeThreads - 1) ? tamanhoArquivo : (i + 1) * bytesPorThread;
            threads.add(new Thread(new TarefaContagemPalavras(arquivo, inicio, fim)));
        }

        // Inicia todas as threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Aguarda todas as threads finalizarem
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        }


        System.out.println("Total de palavras no arquivo: " + totalPalavras);
    }

    // Classe que implementa a tarefa de contagem de palavras
    static class TarefaContagemPalavras implements Runnable {
        private final File arquivo;
        private final long inicio;
        private final long fim;

        public TarefaContagemPalavras(File arquivo, long inicio, long fim) {
            this.arquivo = arquivo;
            this.inicio = inicio;
            this.fim = fim;
        }

        @Override
        public void run() {
            try (RandomAccessFile raf = new RandomAccessFile(arquivo, "r")) {
                raf.seek(inicio); // Posiciona o cursor no ponto inicial
                long posicaoAtual = inicio;
                int contagemLocal = 0;

                while (posicaoAtual < fim) {
                    String linha = raf.readLine();
                    if (linha == null) {
                        break;
                    }
                    String[] palavras = linha.split("\\s+"); // Divide por espaços em branco
                    for (String palavra : palavras) {
                        if (!palavra.isEmpty()) {
                            contagemLocal++;
                        }
                    }
                    posicaoAtual = raf.getFilePointer(); // Atualiza a posição atual
                }

                // Sincroniza o acesso ao total de palavras
                lock.lock();
                try {
                    totalPalavras += contagemLocal;
                } finally {
                    lock.unlock();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
