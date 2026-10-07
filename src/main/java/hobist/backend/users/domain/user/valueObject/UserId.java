package hobist.backend.users.domain.user.valueObject;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@ToString
@EqualsAndHashCode
public class UserId {

    private final @NonNull UUID id;

    public UserId(@NonNull UUID id) {
        if (id==null) throw new IllegalArgumentException("User ID cannot be empty!");

        this.id = id;
    }

    public UUID value() {return id;}
}
