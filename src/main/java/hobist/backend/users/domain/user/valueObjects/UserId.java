package hobist.backend.users.domain.user.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.NonNull;

import java.util.UUID;

@Embeddable
public record UserId(@NonNull UUID id) {


    public UserId {
        if (id==null) throw new IllegalArgumentException("User ID cannot be empty!");
    }

   public static UserId generate() {
        return new UserId(UUID.randomUUID());
   }
}
