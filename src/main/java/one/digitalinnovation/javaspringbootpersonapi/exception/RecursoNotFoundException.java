package one.digitalinnovation.javaspringbootpersonapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNotFoundException extends Exception {
    public RecursoNotFoundException(String message, Long id) {
        super(message + id);
    }
}
