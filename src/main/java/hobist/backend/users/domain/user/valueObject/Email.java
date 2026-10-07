package hobist.backend.users.domain.user.valueObject;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

public record Email(@NonNull String email) {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+" +
            "(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}" +
            "[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$";


    public Email(@NonNull String email) {
        if (email == null) throw new IllegalArgumentException("Email cannot be null!");
        var emailVal=email.strip();
        if (emailVal.length() > 254 || !emailVal.matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Email must be a valid email address!");
        }

        this.email = emailVal;
    }

}
