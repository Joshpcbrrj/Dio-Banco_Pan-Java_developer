package one.digitalinovation.gof.singleton;

/**
 * Singleton "Lazy holder".
 *
 * @see <a href="http://stackoverflow.com/a/24018148">Referencia</a>
 *
 * @author Josué
 */
public class SingletonLazyHolder {

    //Thread safe entre algumas outras vantagens
    private static class InstanceHolder{

        //Instancia definida diretamente no construtor
        public static SingletonLazyHolder instancia = new SingletonLazyHolder();
    }

    private SingletonLazyHolder(){
        //Opcional
        super();
    }

    public static SingletonLazyHolder getInstancia(){
        return InstanceHolder.instancia;
    }
}