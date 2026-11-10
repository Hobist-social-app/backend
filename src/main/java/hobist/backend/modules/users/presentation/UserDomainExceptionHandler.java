package hobist.backend.modules.users.presentation;

import hobist.backend.modules.users.domain.user.Exceptions.UserBannedException;
import hobist.backend.modules.users.domain.user.Exceptions.UserDeletedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserDomainExceptionHandler {

    @ExceptionHandler(UserBannedException.class)
    public ResponseEntity<String> handleBannedUser(UserBannedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserDeletedException.class)
    public ResponseEntity<String> handleDeletedUser(UserDeletedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }


}
