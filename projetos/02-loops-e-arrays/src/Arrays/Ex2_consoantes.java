package Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Ex2_consoantes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] vetor_caracteres = new char[6];
        String consoantes = "";
        int conta_consoantes = 0;

        //Preenchendo o vetor com caracteres
        for (int i = 0; i < vetor_caracteres.length; i++){
            System.out.print("Digite a letra " + (i + 1) + " de 6: " );
            vetor_caracteres[i] = scan.next().charAt(0);

            if((vetor_caracteres[i] != 'a')&&(vetor_caracteres[i] != 'e')&&(vetor_caracteres[i] != 'i')&&
                    (vetor_caracteres[i] != 'o')&&(vetor_caracteres[i] != 'u')){
                conta_consoantes++;
                consoantes += vetor_caracteres[i] + " ";
            }
        }

        //Mostrando na tela
        System.out.println("\nAs letras digitadas são: " + Arrays.toString(vetor_caracteres));
        System.out.println("A quantidade de consoantes é: " + conta_consoantes);
        System.out.println("As consoantes são: " + consoantes);
    }
}