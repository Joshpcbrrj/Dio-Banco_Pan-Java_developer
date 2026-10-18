package one.digitalinovation.gof.singleton;

/**
 * Singleton "Apressado".
 *
 * @author Josué
 */
public class SingletonEager {
    //Instancia definida diretamente no construtor
    public static SingletonEager instancia = new SingletonEager();

    private SingletonEager(){
        super();
    }

    public static SingletonEager getInstancia(){
        return instancia;
    }
}
