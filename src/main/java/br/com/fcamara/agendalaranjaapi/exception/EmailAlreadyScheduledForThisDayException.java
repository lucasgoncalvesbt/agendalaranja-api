package br.com.fcamara.agendalaranjaapi.exception;


public class EmailAlreadyScheduledForThisDayException extends RuntimeException{

    public EmailAlreadyScheduledForThisDayException(String email) {
        super(String.format("O consultor de email %s já possui cadastro para este dia.", email));
    }

}
