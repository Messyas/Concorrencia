package ZZ_ListaDeExercicios.Ex03;

public class TestaBarbearia {
    public static void main(String[] args) throws InterruptedException {
        Barbearia barbearia = new Barbearia(3);  // Sala de espera com 3 cadeiras

        // chegada de clientes
        for (int i = 1; i <= 10; i++) {
            Cliente cliente = new Cliente("Cliente " + i, barbearia);
            new Thread(cliente).start();
            Thread.sleep(1000);  // Simula intervalo entre a chegada dos clientes
        }
    }
}
