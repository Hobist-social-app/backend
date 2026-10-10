package hobist.backend.users.domain.user.valueObjects;

import lombok.NonNull;

public record PasswordHash(@NonNull String password_hash) {

    public PasswordHash(@NonNull String password_hash) {
        if (password_hash == null) throw new IllegalArgumentException("Password hash cannot be null!");

        this.password_hash = password_hash;
    }

}
