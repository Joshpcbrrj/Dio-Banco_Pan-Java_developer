package loops;

import java.util.Scanner;

public class Ex6_Fatorial {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o número que deseja fazer o fatorial: ");
        int numero = scan.nextInt();
        int fatorial = 1;

        for (int i = numero ; i >= 1; i--){
              fatorial = fatorial * i;
        }

        System.out.println("O fatorial de "+ numero + " é: " + fatorial);
    }
}
