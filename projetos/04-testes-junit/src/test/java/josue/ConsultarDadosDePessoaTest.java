package josue;

import josue.junity.BancoDeDados;
import josue.junity.Pessoa;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

public class ConsultarDadosDePessoaTest {

    //Execução única no inicio do teste
    @BeforeAll
    static void configuraConexao (){
        BancoDeDados.iniciarConexao();
        System.out.println("Rodou configuraConexao");
    }

    //Executa antes de cada um dos testes da classe
    @BeforeEach
    void insereDadosParaTeste(){
        BancoDeDados.insereDados(new Pessoa("Joao", LocalDateTime.of(2000,1,1,13,1,1)));
    }
    //Executa depois de cada um dos testes da classe
    @AfterEach
    void removeDadosDoTeste(){
        BancoDeDados.removeDados(new Pessoa("Joao", LocalDateTime.of(2000,1,1,13,1,1)));

    }

    @Test
    void validarDadosDeRetorno(){
        Assertions.assertTrue(true);
    }
    @Test
    void validarDadosDeRetorno2(){
        Assertions.assertNull(null);
    }

    //Execução única no final do teste
    @AfterAll
    static void finalizaConexao(){
        BancoDeDados.finalizarConexao();
        System.out.println("Rodou finaliza conexao");
    }
}
