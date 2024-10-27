package D_PrintTask;

import java.security.SecureRandom;

//classe PrintTask dorme por um tempo aleatório de 0 a 5 segundos
public class PrintTask implements Runnable{
    //SecureRandom é usada para gerar números aleatórios com maior segurança, especialmente em contextos onde a criptografia ou segurança de dados são essenciais.
    private static final SecureRandom generator = new SecureRandom();
    private final int sleepTime;    //tempo de adormecimento aleatório para a thread
    private final String taskName;

    //construtor
    public PrintTask(String taskName) {
        this.taskName = taskName;

        //seleciona o tempo de adromecimento aleatório entre 0 e 5 segundos
        sleepTime = generator.nextInt(5000);    //milissegundos
    }

    //método run contém o código que uma thread executará
    @Override
    public void run() {
        try {//coloca a thread para dormir pela quantidade de tempo sleepTime
            System.out.printf("%s going to sleep for %d milliseconds.%n", taskName,sleepTime);
            Thread.sleep(sleepTime);
        }catch(InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt();//reinterrompe a thread
        }
        //imprime o nome da tarefa
        System.out.printf("%s done sleeping%n",taskName);
    }
}
