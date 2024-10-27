package A_PrimeiraThread;

public class MinhaThread extends Thread {

    @Override
    public void run() {
        for(int i =  1; i < 5; i++) {
            System.out.println(getName() + "Executando o processo: " + i);
            try{
                Thread.sleep(1000); // tempo de intervalo de execucao
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
