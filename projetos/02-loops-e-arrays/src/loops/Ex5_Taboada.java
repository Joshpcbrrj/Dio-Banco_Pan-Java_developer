package loops;

import java.util.Scanner;

public class Ex5_Taboada {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numero;

        do{
            System.out.println("Digite um número entre 1 a 10 para montar a taboada:");
            numero = scan.nextInt();

            if((numero < 1) || (numero > 10)){
                System.out.println("Erro! O número digitado é inválido");
            }
        }while((numero < 1) || (numero > 10));

        System.out.println("Taboada de: " + numero);

        for (int i = 0; i <= 9; i++){
            System.out.println(numero + " x "+ (i + 1)+ " = " + (numero * (i + 1))) ;
        }
    }
}
