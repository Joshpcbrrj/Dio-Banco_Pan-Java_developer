package one.digitalinovation.gof.strategy;

public class ComportamentoDefensivo implements Comportamento{

    //Sobreescrevendo método "mover"
    @Override
    public void mover() {
        System.out.println("Movendo-se defensivamente...");
    }
}
