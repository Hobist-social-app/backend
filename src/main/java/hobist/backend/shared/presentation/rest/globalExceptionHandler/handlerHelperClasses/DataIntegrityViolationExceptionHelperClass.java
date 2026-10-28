package hobist.backend.shared.presentation.rest.globalExceptionHandler.handlerHelperClasses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class DataIntegrityViolationExceptionHelperClass {

    public static ResponseEntity<String> handleInstanceOfException(Throwable ex) {

        //M.G: cause(inner exception) is exception wrapped by exception.
        var cause=ex;

        while(cause != null) {


            if (cause instanceof org.hibernate.exception.ConstraintViolationException cve) {
                return constraintViolationExceptionHelper(cve);
            }

            cause=cause.getCause();
        }

       return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body("Data Integrity Violation");
    }


    private static ResponseEntity<String> constraintViolationExceptionHelper(org.hibernate.exception.ConstraintViolationException cve) {
        if ("uq_users_email".equals(cve.getConstraintName())) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }
        else if ("uq_users_nickname".equals(cve.getConstraintName())) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("nickname already exists");
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("unknown constraint violation");
    }
}
