package hobist.backend.modules.users.domain.user.valueObjects;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record Email(@NonNull String email) {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+" +
            "(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}" +
            "[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$";


    public Email(@NonNull String email) {
        if (email == null) throw new InvalidArgumentException("Email cannot be null!");
        var emailVal=email.strip();
        if (emailVal.length() > 254 || !emailVal.matches(EMAIL_PATTERN)) {
            throw new InvalidArgumentException("Email must be a valid format!");
        }

        this.email = emailVal;
    }

}
