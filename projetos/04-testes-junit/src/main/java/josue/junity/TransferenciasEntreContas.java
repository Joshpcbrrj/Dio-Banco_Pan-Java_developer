package josue.junity;

public class TransferenciasEntreContas {
    public void transfere(Conta conOrigem, Conta contaDestino, int valor){
        if(valor <= 0){
            throw  new IllegalArgumentException("Erro! Valor deve ser maior que zero");
        }
    }
}
