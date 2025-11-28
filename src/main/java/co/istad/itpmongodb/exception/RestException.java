package co.istad.itpmongodb.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleRestException (ResponseStatusException exception){
            Map<String,Object> error = new HashMap<>();
            error.put("status",exception.getStatusCode().value());
            error.put("message", exception.getMessage());
            error.put("timestamp",System.currentTimeMillis());
            return  ResponseEntity.status(exception.getStatusCode()).body(error);
    }
}
