package Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Ex1_ordem_inversa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] vetor_6_num = new int[6];
        int[] vetor_inverso = new int[vetor_6_num.length];

        //preenchendo o array
        for (int i = 0; i < vetor_6_num.length; i++){
            System.out.println("Digite o número " + (i + 1)+ " de 6: " );
            vetor_6_num[i] = scan.nextInt();
        }

        //invertendo a ordem do array
        for (int i = vetor_6_num.length - 1; i >= 0; i--){
            vetor_inverso[(vetor_6_num.length - 1 - i)] = vetor_6_num[i];
        }

        //Mostrando os dados na tela
        System.out.println("Array na ordem normal: " + Arrays.toString(vetor_6_num));
        System.out.println("Array na ordem inversa: " + Arrays.toString(vetor_inverso));
    }
}
