package one.digitalinovation.gof.strategy;

public class ComportamentoNormal implements Comportamento{

    //Sobreescrevendo método "mover"
    @Override
    public void mover() {
        System.out.println("Movendo-se normalmente...");
    }
}
