package hobist.backend.users.domain.user.valueObject;

import lombok.NonNull;

public record Nickname(@NonNull String nickname) {


    public Nickname(@NonNull String nickname) {
        if (nickname == null || nickname.isBlank()) throw new IllegalArgumentException("Nickname cannot be empty!");

        this.nickname = nickname;
    }

}
