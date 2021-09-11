package br.com.fcamara.hackatonapi.exception;

public class EmailAlreadyHasAnAccount extends RuntimeException{

    public EmailAlreadyHasAnAccount(String email) {
        super(String.format("O email %s já pussui uma conta registrada", email));
    }

}
