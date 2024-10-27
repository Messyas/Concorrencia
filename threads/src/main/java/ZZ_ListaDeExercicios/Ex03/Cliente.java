package ZZ_ListaDeExercicios.Ex03;

public class Cliente implements Runnable {
    private final String nome;
    private final Barbearia barbearia;

    public Cliente(String nome, Barbearia barbearia) {
        this.nome = nome;
        this.barbearia = barbearia;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void run() {
        try {
            barbearia.chegarNaBarbearia(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
