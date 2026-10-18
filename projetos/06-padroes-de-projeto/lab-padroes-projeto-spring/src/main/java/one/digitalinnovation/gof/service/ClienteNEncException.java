package one.digitalinnovation.gof.service;

public class ClienteNEncException extends RuntimeException {

    public ClienteNEncException(Long id) {
        super("Cliente não encontrado com o ID: " + id);
    }

}