package loops;

import java.util.Arrays;
import java.util.Scanner;

public class Ex3_maior_numero_e_media {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numero, soma_numeros = 0, maior_numero = 0;

        for(int i = 0; i < 5; i++){
            System.out.println("Digite o número " + (i+1) + " de 5:" );
            numero = scan.nextInt();

            if(numero > maior_numero){
                maior_numero = numero;
            }

            soma_numeros += numero;
        }

        //Mostrando dados na tela
        System.out.println("O maior número entre os 5 digitados é: " + maior_numero);
        System.out.println("A média dos 5 números digitados é: " + (soma_numeros / 5));
    }
}
