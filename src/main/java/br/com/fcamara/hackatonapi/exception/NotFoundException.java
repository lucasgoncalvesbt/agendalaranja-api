package br.com.fcamara.hackatonapi.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String id, String modelo) {
        super(String.format("%s de ID %s não encontrado",modelo, id));
    }

}
