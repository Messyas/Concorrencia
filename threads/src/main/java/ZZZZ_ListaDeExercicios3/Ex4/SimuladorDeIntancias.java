package ZZZZ_ListaDeExercicios3.Ex4;

public class SimuladorDeIntancias {
    public static void main(String[] args) {
        Servidor servidor = new Servidor();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> servidor.usarRecurso());
            thread.start();
        }
    }
}
