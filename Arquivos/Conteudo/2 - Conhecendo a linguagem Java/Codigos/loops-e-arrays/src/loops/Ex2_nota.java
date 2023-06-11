package loops;

import java.util.Scanner;

public class Ex2_nota {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numero;

        do{
            System.out.println("Digite um número: ");
            numero = scan.nextInt();
            if((numero < 0) || (numero > 10)){
                System.out.println("Erro! Número inválido.");
            }
        }while((numero < 0) || (numero > 10));

        System.out.println("O número digitado foi: " + numero);
    }
}
