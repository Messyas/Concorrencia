package F_ExemploCerto;

//Executando dois Runnables para adicionar elementos a um SimpleArray compartilhado.
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class SharedArrayTest{
    public static void main(String[] arg){
        // constrói o objeto compartilhado
        SimpleArraySincronizado SimpleArraySincronizado = new SimpleArraySincronizado(6);

        // cria duas tarefas a gravar no SimpleArray compartilhado
        ArrayWriter writer1 = new ArrayWriter(1, SimpleArraySincronizado);
        ArrayWriter writer2 = new ArrayWriter(11, SimpleArraySincronizado);

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
                System.out.println(SimpleArraySincronizado    ); // imprime o conteúdo
            } else
                System.out.println("Timed out while waiting for tasks to finish.");
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    } // fim de main
} // fim da classe SharedArrayTest