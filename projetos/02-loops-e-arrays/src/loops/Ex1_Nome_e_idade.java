package loops;

import java.util.Scanner;

public class Ex1_Nome_e_idade {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true){
            //Pedindo dados
            System.out.println("Digite o nome do aluno: ");
            String nome_aluno = input.next();

            //Condição para a saida
            if(nome_aluno.equals("0")) break;
            System.out.println("Digite a idade do aluno: ");
            int idade_aluno = input.nextInt();
            System.out.println("Aluno: " + nome_aluno + " idade: " + idade_aluno );
        }
    }
}
