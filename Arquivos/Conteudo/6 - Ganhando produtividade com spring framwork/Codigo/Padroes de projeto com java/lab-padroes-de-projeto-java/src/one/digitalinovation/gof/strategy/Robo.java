package one.digitalinovation.gof.strategy;

public class Robo {
    private Comportamento estrategiaDeComportamento;

    //Método set -> usado para mudança de comportamento do Robo
    public void setEstrategiaDeComportamento(Comportamento estrategiaDeComportamento) {
        this.estrategiaDeComportamento = estrategiaDeComportamento;
    }

    public void mover(){
        estrategiaDeComportamento.mover();
    }
}
