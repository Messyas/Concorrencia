package E_ExemploErrado;

//Executando dois Runnables para adicionar elementos a um SimpleArray compartilhado.
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * O problema aqui é que como nao existe o controle da sincronizacao e nao tem o conceito de monitor, devido a isso o espaco em memoria
 * pode ser sobrescrito, o que esta incorredo pois a memoria devia ser alocada e o sistema nao deveria permitir que outro programa acesse essa memoria.
 *
 */

public class SharedArrayTest{
    public static void main(String[] arg){
        // constrói o objeto compartilhado
        SimpleArray sharedSimpleArray = new SimpleArray(6);

        // cria duas tarefas a gravar no SimpleArray compartilhado
        ArrayWriter writer1 = new ArrayWriter(1, sharedSimpleArray);
        ArrayWriter writer2 = new ArrayWriter(11, sharedSimpleArray);

        // executa as tarefas com um ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(writer1);
        executorService.execute(writer2);

        executorService.shutdown();

        try{
            // espera 1 minuto para que ambos os escritores terminem a execução
            boolean tasksEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);

            if (tasksEnded){
                System.out.printf("%nContents of SimpleArray:%n");
                System.out.println(sharedSimpleArray); // imprime o conteúdo
            } else
                System.out.println("Timed out while waiting for tasks to finish.");
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    } // fim de main
} // fim da classe SharedArrayTest