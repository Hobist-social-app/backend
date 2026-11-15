package hobist.backend.modules.users.domain.user.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserBannedException extends RuntimeException {
    public UserBannedException(String message) {
        super(message);
    }
}
