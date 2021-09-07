package br.com.fcamara.hackatonapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String id, String modelo) {
        super(String.format("%s de ID %s não encontrado",modelo, id));
    }

}
