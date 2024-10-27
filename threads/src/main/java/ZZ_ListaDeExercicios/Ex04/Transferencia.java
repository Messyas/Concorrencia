package ZZ_ListaDeExercicios.Ex04;

import java.util.Random;

class Transferencia implements Runnable {
    private final Conta[] contas;
    private final Random random = new Random();

    public Transferencia(Conta[] contas) {
        this.contas = contas;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            int deConta = random.nextInt(contas.length);
            int paraConta = random.nextInt(contas.length);
            int quantia = random.nextInt(100);

            if (deConta != paraConta) {
                Conta.transferir(contas[deConta], contas[paraConta], quantia);
            }
        }
    }
}