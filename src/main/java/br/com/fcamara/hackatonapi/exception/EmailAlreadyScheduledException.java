package br.com.fcamara.hackatonapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyScheduledException extends RuntimeException{

    public EmailAlreadyScheduledException(String email) {
        super(String.format("O consultor de email %s já possui cadastro para este dia.", email));
    }

}
