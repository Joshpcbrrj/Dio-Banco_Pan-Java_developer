package one.digitalinovation.gof.singleton;

/**
 * Singleton "Preguiçoso".
 *
 * @author Josué
 */
public class SingletonLazy {
    private static SingletonLazy instancia;

    //Construtor privado para impedir que outras classes usem
    public SingletonLazy() {

        //Opcional
        super();
    }

    public static SingletonLazy getInstancia(){
        if(instancia == null){
            instancia = new SingletonLazy();
        }
        return instancia;
    }
}
