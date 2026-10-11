package hobist.backend.users.domain.user.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

@Embeddable
public record Nickname(@NonNull String nickname) {

    public Nickname(@NonNull String nickname) {
        if ( nickname.isBlank()) throw new IllegalArgumentException("Nickname cannot be empty!");

        this.nickname = nickname;
    }

}
