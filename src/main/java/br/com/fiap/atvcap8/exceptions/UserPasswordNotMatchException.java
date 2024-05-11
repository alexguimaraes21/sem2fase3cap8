package br.com.fiap.atvcap8.exceptions;

public class UserPasswordNotMatchException extends RuntimeException {

    public UserPasswordNotMatchException(String message) {
        super(message);
    }
}
