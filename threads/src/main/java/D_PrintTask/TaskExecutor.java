package D_PrintTask;

//Usando um ExecutorService para executar Runnables.
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TaskExecutor{
    public static void main(String[] args){
        // cria e nomeia cada executável
        PrintTask task1 = new PrintTask("task1");
        PrintTask task2 = new PrintTask("task2");
        PrintTask task3 = new PrintTask("task3");

        System.out.println("Starting Executor");

        // cria ExecutorService para gerenciar threads
        ExecutorService executorService = Executors.newCachedThreadPool();
        // inicia as três PrintTasks
        executorService.execute(task1); // inicia task1ÿ
        executorService.execute(task2); // inicia task2ÿ
        executorService.execute(task3); // inicia task3ÿ

        // fecha ExecutorService -- ele decide quando fechar threads
        executorService.shutdown();

        System.out.printf("Tasks started, main ends.%n%n");
    }
} // fim da classe TaskExecutor