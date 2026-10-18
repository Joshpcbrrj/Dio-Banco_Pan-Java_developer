package Arrays;

import java.util.Random;

public class ex3_numeros_aleatorios {
    public static void main(String[] args) {
        Random random = new Random();
        int[] vetor_numeros_aleatorios = new int[20];

        for (int i = 0; i < vetor_numeros_aleatorios.length; i++){
            int numero = random.nextInt(0,100);
            vetor_numeros_aleatorios[i] = numero;
        }

        System.out.print("Numeros aleatorios: ");
        for(int numero : vetor_numeros_aleatorios){
            System.out.print(numero + " ");
        }
        System.out.print("\nSucessores dos números: ");
        for(int numero : vetor_numeros_aleatorios){
            System.out.print((numero + 1) + " ");
        }
    }
}
