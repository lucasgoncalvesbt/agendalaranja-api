package br.com.fcamara.hackatonapi.exception;

public class UserDoesNotHavePermissionException extends RuntimeException {

    public UserDoesNotHavePermissionException() {
        super("Você não tem permissão para acessar ou registrar dados para este usuario");
    }
}
