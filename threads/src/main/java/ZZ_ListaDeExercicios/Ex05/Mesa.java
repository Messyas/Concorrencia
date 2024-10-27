package ZZ_ListaDeExercicios.Ex05;

import java.util.concurrent.Semaphore;

class Mesa {
    // Os semáforos que representam os garfos compartilhados entre os filósofos
    private final Semaphore[] garfos;

    public Mesa() {
        garfos = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            garfos[i] = new Semaphore(1); // Cada garfo começa disponível (valor 1)
        }
    }

    public void pegarGarfos(int filosofoId) throws InterruptedException {
        int garfoDireito = (filosofoId + 1) % 5;

        // Os filósofos pegam primeiro o garfo da esquerda e depois o da direita
        if (filosofoId % 2 == 0) { // Para evitar deadlock, alterne a ordem
            garfos[filosofoId].acquire();  // Pega o garfo esquerdo
            garfos[garfoDireito].acquire();   // Pega o garfo direito
        } else {
            garfos[garfoDireito].acquire();   // Pega o garfo direito
            garfos[filosofoId].acquire();  // Pega o garfo esquerdo
        }
    }

    public void soltarGarfos(int filosofoId) {
        int garfoDireito = (filosofoId + 1) % 5;

        // Solta os garfos
        garfos[filosofoId].release();
        garfos[garfoDireito].release();
    }
}