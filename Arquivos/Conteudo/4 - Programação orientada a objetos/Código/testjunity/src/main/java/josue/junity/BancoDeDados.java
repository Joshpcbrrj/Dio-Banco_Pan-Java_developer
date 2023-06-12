package josue.junity;

import java.util.logging.Logger;

public class BancoDeDados {
    public static final Logger LOGGER = Logger.getLogger(BancoDeDados.class.getName());

    public static void iniciarConexao(){
        LOGGER.info("Iniciou conexao");
    }

    public static void finalizarConexao(){
        LOGGER.info("Finalizou conexao");
    }

    public static void insereDados(Pessoa pessoa){

        //insere pessoa no DB
        LOGGER.info("Inseriu dados");
    }
    public static void removeDados(Pessoa pessoa){

        //Remove pessoa no DB
        LOGGER.info("Removeu dados");
    }

}
