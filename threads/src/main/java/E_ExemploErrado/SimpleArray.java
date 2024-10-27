package E_ExemploErrado;

/**
 * Essa classe é um exemplo do que nao se deve fazer em relacao a arrays
 *
 **/
//Classe que gerencia um array de inteiros para ser compartilhado por várias threads.
import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray{ // ATENÇÃO: NÃO SEGURO PARA THREADS!
    private static final SecureRandom generator = new SecureRandom();
    private final int[] array; // array de inteiros compartilhado
    private int writeIndex = 0; // índice compartilhado do próximo elemento a gravar

    // cria um SimpleArray de um determinado tamanho
    public SimpleArray(int size){
        array = new int[size];
    }

    // adiciona um valor ao array compartilhado
    public void add(int value){
        int position = writeIndex; // armazena o índice de gravação

        try{
            // coloca a thread para dormir durante 0 a 499 milissegundos
            Thread.sleep(generator.nextInt(500));
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt(); // reinterrompe a thread
        }

        // coloca o valor no elemento apropriado
        array[position] = value;
        System.out.printf("%s wrote %2d to element %d.%n", Thread.currentThread().getName(), value, position);

        ++writeIndex; // índice de incremento de elemento a ser gravado depois
        System.out.printf("Next write index: %d%n", writeIndex);
    }
    // usado para gerar saída do conteúdo do array de inteiros compartilhado
    public String toString(){
        return Arrays.toString(array);
    }
} // fim da classe SimpleArray
