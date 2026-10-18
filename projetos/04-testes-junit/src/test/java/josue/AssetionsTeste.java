package josue;

import josue.junity.Pessoa;
import org.junit.jupiter.api.Assertions; //Depois ver importação estátitca (com "Static)
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

public class AssetionsTeste {
    @Test
    void validarLancamentos(){

        int[] array_1 = {10, 20, 30, 40, 50};
        int[] array_2 = {10, 20, 30, 40, 50};
        int[] array_3 = {-1, 5, 2,3, 10, 16, 17};

        //Verifica se as posições dos arrays são iguais -> "assetArrayEquals"
        Assertions.assertArrayEquals(array_1, array_2);

        //Verifica se as posições dos arrays são diferentes ->  "assetArrayEquals"
        Assertions.assertNotEquals(array_1, array_3);
    }

    @Test
    void validaSeObjetoEstaNulo(){
        Pessoa pessoa1 = null;
        Pessoa pessoa2 = new Pessoa("Luciana", LocalDateTime.now());

        //Verifica se esta nulo
        Assertions.assertNull(pessoa1);

        //Verifica se não esta nulo
        Assertions.assertNotNull(pessoa2);
    }

    @Test
    void validarNumerosDeTiposDiferentes(){
        double valor1 = 5.0;
        double valor2 = 5.0;

        //Verifica se os calores são iguais
        Assertions.assertEquals(valor1, valor2);
    }
}
