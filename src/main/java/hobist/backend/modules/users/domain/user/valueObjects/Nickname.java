package hobist.backend.modules.users.domain.user.valueObjects;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record Nickname(@NonNull String nickname) {

    public Nickname(@NonNull String nickname) {
        if ( nickname.isBlank()) throw new InvalidArgumentException("Nickname cannot be empty!");

        this.nickname = nickname;
    }

}
