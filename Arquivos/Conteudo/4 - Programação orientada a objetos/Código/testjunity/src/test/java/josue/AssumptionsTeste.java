package josue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class AssumptionsTeste {
    @Test
    void validarAlgoNoUsuarioJosue(){
        //Presuma que estou logado com "Josue"
        Assumptions.assumeTrue("Josue".equals(System.getenv("USER")));
        Assertions.assertEquals(10, 5+5);
    }
}
