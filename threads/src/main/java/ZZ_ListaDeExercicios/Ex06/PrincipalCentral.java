package ZZ_ListaDeExercicios.Ex06;

// Classe principal para rodar a simulação
public class PrincipalCentral {
    public static void main(String[] args) {
        CentralImpressao centralImpressao = new CentralImpressao(5); // Limite de 5 impressões antes da manutenção


        Thread computador1 = new Thread(new Computador(centralImpressao, "Documento 1"), "Computador 1");
        Thread computador2 = new Thread(new Computador(centralImpressao, "Documento 2"), "Computador 2");
        Thread computador3 = new Thread(new Computador(centralImpressao, "Documento 3"), "Computador 3");


        Thread tecnico = new Thread(new Tecnico(centralImpressao), "Técnico");


        computador1.start();
        computador2.start();
        computador3.start();
        tecnico.start();
    }
}


