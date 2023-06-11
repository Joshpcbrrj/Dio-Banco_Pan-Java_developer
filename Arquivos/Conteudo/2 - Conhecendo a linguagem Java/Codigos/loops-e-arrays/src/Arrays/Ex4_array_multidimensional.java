package Arrays;

import java.util.Arrays;
import java.util.Random;

public class Ex4_array_multidimensional {
    public static void main(String[] args) {
        int[][] matriz = new int[4][4];
        Random random = new Random();

        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = random.nextInt(0,9);
            }
        }
        System.out.println("Matriz de números aleatórios entre 0 e 9:");
        for(int[] linha : matriz){
            for (int coluna: linha){
                System.out.print(coluna + " ");
            }
            System.out.println("");
        }
    }
}
