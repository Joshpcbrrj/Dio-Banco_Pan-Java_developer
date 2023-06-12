package josue;

//Precisaremos desse import
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Importando a classe "Pessoa" do pacote java
import josue.junity.Pessoa;

import java.time.LocalDateTime;

public class PessoaTeste {

    //Começamos com a anotação "@Teste"
    /*Todos os testes do JUnity são retorno "void" (Sem retorno)*/

    //Testando "getIdade()" -> assertEquals para resultado int
    @Test
    void deveCalcularIdadeCorretamente(){
        Pessoa Brunin = new Pessoa("Brunin", LocalDateTime.of(2000, 1, 1, 15,0,0));
        Assertions.assertEquals(23, Brunin.getIdade());
    }

    //Testando "ehMaiorDeIdade()" -> assertTrue/assertFalse para resultado Boolean
    @Test
    void deveRetornarSeEMaiorDeIdade(){
        //assertTrue
        Pessoa Elaine = new Pessoa("Elaine", LocalDateTime.of(1990, 1, 1, 15,0,0));
        Assertions.assertTrue(Elaine.ehMaiorDeIdade());

        //assertFalse
        Pessoa Maria = new Pessoa("maria", LocalDateTime.of(2010, 1, 1, 15,0,0));
        Assertions.assertFalse(Maria.ehMaiorDeIdade());
    }
}
