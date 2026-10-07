package hobist.backend.users.domain.user.valueObject;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Nickname {

    private final @NonNull String nickname;

    public Nickname(@NonNull String nickname) {
        if (nickname == null || nickname.isBlank()) throw new IllegalArgumentException("Nickname cannot be empty!");

        this.nickname = nickname;
    }

    public String value() {return nickname;}
}
