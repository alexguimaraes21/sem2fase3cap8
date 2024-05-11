package br.com.fiap.atvcap8.exceptions;

public class RegisteredUserException extends RuntimeException {

    public RegisteredUserException(String message) {
        super(message);
    }
}
