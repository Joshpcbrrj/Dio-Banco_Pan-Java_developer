package one.digitalinovation.gof.strategy;

public class ComportamentoOfensivo implements Comportamento{

    //Sobreescrevendo método "mover"
    @Override
    public void mover() {
        System.out.println("Movendo-se ofensivamente...");
    }
}
