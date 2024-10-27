package F_ExemploCerto;

//Classe que gerencia um array de inteiros a ser compartilhado por múltiplas
// threads com sincronização.
import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArraySincronizado {
    private static final SecureRandom generator = new SecureRandom();
    private final int[] array; // array de inteiros compartilhado
    private int writeIndex = 0; // índice do próximo elemento a ser gravado

    // cria um SimpleArray de um determinado tamanho
    public SimpleArraySincronizado(int size){
        array = new int[size];
    }

    // adiciona um valor ao array compartilhado
    public synchronized void add(int value){
        int position = writeIndex; // armazena o índice de gravação

        try{
            // em aplicativos reais, não se deve dormir enquanto se mantém um bloqueio
            Thread.sleep(generator.nextInt(500)); // apenas para demonstração
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }

        // coloca o valor no elemento apropriado
        array[position] = value;
        System.out.printf("%s wrote %2d to element %d.%n",
                Thread.currentThread().getName(), value, position);

        ++writeIndex; // incrementa índice de elemento a ser gravado depois
        System.out.printf("Next write index: %d%n", writeIndex);
    }

    // usado para gerar o conteúdo do array de inteiros compartilhado
    public synchronized String toString(){
        return Arrays.toString(array);
    }
} // fim da classe SimpleArray