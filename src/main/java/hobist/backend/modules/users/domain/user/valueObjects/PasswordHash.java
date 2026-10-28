package hobist.backend.modules.users.domain.user.valueObjects;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record PasswordHash(@NonNull String password_hash) {

    public PasswordHash(@NonNull String password_hash) {
        if (password_hash == null) throw new InvalidArgumentException("Password hash cannot be null!");

        this.password_hash = password_hash;
    }

}
