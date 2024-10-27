package B_MinhaThreadRunnable;

public class MinhaThreadRunnable implements Runnable {

    @Override
    public void run() {
        for(int i =  1; i < 5; i++) {
            System.out.println(Thread.currentThread()+ "Executando o processo: " + i);
            try{
                Thread.sleep(5000); // tempo de intervalo de execucao
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
