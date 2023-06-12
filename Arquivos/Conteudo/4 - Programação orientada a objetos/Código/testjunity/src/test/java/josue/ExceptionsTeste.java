package josue;

import josue.junity.Conta;
import josue.junity.TransferenciasEntreContas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionsTeste {
    @Test
    void validaCenarioDeExcecaoNaTransferencia(){
        Conta conta_origem = new Conta("123456", 10);
        Conta conta_destino = new Conta("123457", 100);

        TransferenciasEntreContas transferencia1 = new TransferenciasEntreContas();

        //Fazendo verificação para confirmar se temos excecão
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                        transferencia1.transfere(conta_origem, conta_destino, -1));

        //Verificando se nao vai lancar excecão
        Assertions.assertDoesNotThrow(() -> transferencia1.transfere(conta_origem, conta_destino, 10));
    }
}
