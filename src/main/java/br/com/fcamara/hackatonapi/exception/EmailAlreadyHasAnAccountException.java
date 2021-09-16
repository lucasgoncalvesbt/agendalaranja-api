package br.com.fcamara.hackatonapi.exception;

public class EmailAlreadyHasAnAccountException extends RuntimeException{

    public EmailAlreadyHasAnAccountException(String email) {
        super(String.format("O email %s já pussui uma conta registrada", email));
    }

}
