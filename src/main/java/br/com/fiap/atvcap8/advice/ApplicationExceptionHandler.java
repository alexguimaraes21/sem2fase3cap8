package br.com.fiap.atvcap8.advice;

import br.com.fiap.atvcap8.exceptions.RegisteredUserException;
import br.com.fiap.atvcap8.exceptions.UserPasswordNotMatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodArgumentNotValid(MethodArgumentNotValidException erro) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> fields = erro.getBindingResult().getFieldErrors();
        for (FieldError field : fields) {
            errorMap.put(field.getField(), field.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> dataIntegrityViolation(DataIntegrityViolationException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", erro.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> resourceNotFound(EntityNotFoundException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", erro.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RegisteredUserException.class)
    public Map<String, String> registeredUser(RegisteredUserException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", erro.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserPasswordNotMatchException.class)
    public Map<String, String> userPasswordNotMatch(UserPasswordNotMatchException erro) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", erro.getMessage());
        return errorMap;
    }
}
