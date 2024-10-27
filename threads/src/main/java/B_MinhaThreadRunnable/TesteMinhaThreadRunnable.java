package B_MinhaThreadRunnable;

public class TesteMinhaThreadRunnable {
    public static void main(String[] args) {

        Thread t1 = new Thread(new MinhaThreadRunnable());
        Thread t2 = new Thread(new MinhaThreadRunnable());

        t1.start();
        t2.start();
    }
}
