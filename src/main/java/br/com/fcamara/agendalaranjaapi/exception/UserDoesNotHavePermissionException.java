package br.com.fcamara.agendalaranjaapi.exception;

public class UserDoesNotHavePermissionException extends RuntimeException {

    public UserDoesNotHavePermissionException() {
        super("Você não tem permissão para acessar ou registrar dados para este email");
    }
}
