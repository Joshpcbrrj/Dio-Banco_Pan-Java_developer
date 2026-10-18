package loops;

import java.util.Scanner;

public class Ex4_par_ou_impar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int quant_numeros;
        System.out.println("Digite a quantidade de números que irá digitar: ");
        quant_numeros = scan.nextInt();

        int numero;
        int quant_numeros_pares = 0, quant_numeros_impares =0;
        for (int i = 0; i < quant_numeros; i++){
            System.out.println("Digite o número " + (i + 1) +" de " + quant_numeros +":" );
            numero = scan.nextInt();

            if ((numero % 2) == 0) quant_numeros_pares++;
            else quant_numeros_impares++;
        }

        //Mostrando dados na tela
        System.out.println("A quantidade de números digitados foi: " + quant_numeros);
        System.out.println("Temos: " + quant_numeros_pares + " números pares, e: "
                + quant_numeros_impares + " números impares.");
    }
}
