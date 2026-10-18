package josue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

public class CondicionaisTeste {

    @Test
    @EnabledIfEnvironmentVariable(named = "user", matches = "Carmen")
    void validaSomenteNoUsuarioCarmen(){
        Assertions.assertEquals(5, 3 + 2);
    }
}
