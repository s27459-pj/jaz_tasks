package pw.karczewski.tasks.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends APIException {
    public ValidationException(String message) {
        super("Validation failed", HttpStatus.BAD_REQUEST);
    }
}
