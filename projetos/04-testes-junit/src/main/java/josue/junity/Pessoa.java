package josue.junity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Pessoa {
    //Atributos
    private String nome;
    private LocalDateTime nascimento;

    //Construtor
    public Pessoa(String nome, LocalDateTime nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    //Getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDateTime getNascimento() {
        return nascimento;
    }
    public void setNascimento(LocalDateTime nascimento) {
        this.nascimento = nascimento;
    }

    //Metodo para informar a idade
    public int getIdade(){
        return (int) ChronoUnit.YEARS.between(nascimento, LocalDateTime.now());
    }

    //Metodo para avaliar se é maior de idade
    public boolean ehMaiorDeIdade(){
        return getIdade() >= 18;
    }
}
