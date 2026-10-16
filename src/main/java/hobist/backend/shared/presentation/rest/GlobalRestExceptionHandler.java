package hobist.backend.shared.presentation.rest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
           IllegalArgumentException ex){
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity<String> handleDataIntegrityViolation(Exception ex){

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpected(Exception ex) {

        return ResponseEntity
                .internalServerError()
                .body("Unexpected error occurred");
    }
}
