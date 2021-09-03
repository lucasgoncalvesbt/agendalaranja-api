package br.com.fcamara.hackatonapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CapacityExceededException extends RuntimeException{

    public CapacityExceededException(Double ultrapassou) {
        super(String.format("A quantidade máxima de colaboradores foi ultrapassada em %s", ultrapassou));
    }

}
