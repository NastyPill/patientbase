package ru.patientbase.mainAPI.rest.exceptionAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class RestControllerAdvice {

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("\"" + ex.getMessage() + "\"");
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("\"" + ex.getMessage() + "\"");
    }

    @ExceptionHandler({IllegalAccessException.class})
    public ResponseEntity<String> handleIllegalAccessException(IllegalAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("\"" + ex.getMessage() + "\"");
    }

}
