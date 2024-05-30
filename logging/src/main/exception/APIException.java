package pw.karczewski.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    private HttpStatus status;

    public APIException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
