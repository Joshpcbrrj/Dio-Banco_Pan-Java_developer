package one.digitalinovation.gof;

import one.digitalinovation.gof.facade.Facade;
import one.digitalinovation.gof.singleton.SingletonEager;
import one.digitalinovation.gof.singleton.SingletonLazy;
import one.digitalinovation.gof.singleton.SingletonLazyHolder;
import one.digitalinovation.gof.strategy.*;


public class Main {

    public static void main(String[] args) {
        /*Teste design pattern Singleton (Usa mesma referência de memória)*/
        //Instanciando a partir de "SingletonLazy"
        SingletonLazy lazy = SingletonLazy.getInstancia();
        System.out.println("Endereço da 1° atribuição 'lazy': " + lazy);

        lazy = SingletonLazy.getInstancia();
        System.out.println("Endereço da 2° atribuição 'lazy': " + lazy);

        //Instanciando a partir de "SingletonEager"
        SingletonEager eager = SingletonEager.getInstancia();
        System.out.println("Endereço da 1° atribuição 'eager': " +eager);

        eager = SingletonEager.getInstancia();
        System.out.println("Endereço da 2° atribuição 'eager': " +eager);

        //Instanciando a partir de "SingletonLazyHolder"
        SingletonLazyHolder lazyHolder = SingletonLazyHolder.getInstancia();
        System.out.println("Endereço da 1° atribuição 'lazyHolder': " + lazyHolder);

        lazyHolder= SingletonLazyHolder.getInstancia();
        System.out.println("Endereço da 2° atribuição 'lazyHolder': " + lazyHolder);


        /*Teste design pattern strategy (usa polimorfismo)*/
        //Definindo comportamentos possíveis para o robo
        Comportamento defensivo = new ComportamentoDefensivo();
        Comportamento normal = new ComportamentoNormal();
        Comportamento ofensivo = new ComportamentoOfensivo();

        //criando robo com comportamento normal
        Robo r1= new Robo();
        r1.setEstrategiaDeComportamento(normal);
        r1.mover();

        r1.setEstrategiaDeComportamento(ofensivo);
        r1.mover();
        r1.mover();

        r1.setEstrategiaDeComportamento(defensivo);
        r1.mover();
        r1.mover();
        r1.mover();

        /*Teste design pattern facade (Simplifica cominicação de dados)*/
        Facade facade = new Facade();
        facade.migrarCliente("Josué", "000.000.01");
    }
}
