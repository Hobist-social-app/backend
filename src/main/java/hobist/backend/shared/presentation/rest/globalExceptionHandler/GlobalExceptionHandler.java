package hobist.backend.shared.presentation.rest.globalExceptionHandler;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import hobist.backend.shared.presentation.rest.globalExceptionHandler.handlerHelperClasses.DataIntegrityViolationExceptionHelperClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<String> handleInvalidArgument(InvalidArgumentException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex){

        log.error("Unexpected error while processing request", ex);

        var response = DataIntegrityViolationExceptionHelperClass
                .handleInstanceOfException(ex);

        return response;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpected(Exception ex) {

        log.error("Unexpected error while processing request", ex);

        return ResponseEntity
                .internalServerError()
                .body("Unexpected error occurred");
    }
}
