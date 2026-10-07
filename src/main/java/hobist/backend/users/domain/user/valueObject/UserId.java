package hobist.backend.users.domain.user.valueObject;

import lombok.NonNull;

import java.util.UUID;

public record UserId(@NonNull UUID id) {


    public UserId {
        if (id==null) throw new IllegalArgumentException("User ID cannot be empty!");
    }
}
