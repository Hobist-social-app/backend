package hobist.backend.shared.presentation.rest.globalexceptionhandler;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import hobist.backend.shared.presentation.rest.globalexceptionhandler.handlerhelperclasses.DataIntegrityViolationExceptionHelperClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid credentials");
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<String> handleInvalidArgument(InvalidArgumentException ex) {

        log.error("Unexpected error while processing request", ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        log.error("Unexpected error while processing request", ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid arguments");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex){

        log.error("Unexpected error while processing request", ex);

        var response = DataIntegrityViolationExceptionHelperClass
                .handleInstanceOfException(ex);

        return response;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        log.error("Unexpected error while processing request", ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request body. Check its format and field values.");
    }


    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<String> handleNotImplementedFeature(NotImplementedException ex) {

        log.error("Unexpected error while processing request", ex);

        return ResponseEntity
                .internalServerError()
                .body("You reached feature that was not implemented yet!");
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleUnexpected(IllegalStateException ex) {

        log.error("Unexpected error while processing request", ex);

        return ResponseEntity
                .internalServerError()
                .body("Unexpected error occurred");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpected(Exception ex) {

        log.error("Unexpected error while processing request", ex);

        return ResponseEntity
                .internalServerError()
                .body("Unexpected error occurred");
    }
}
