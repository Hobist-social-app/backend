package hobist.backend.shared.domain.valueObjects;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.Embeddable;
import lombok.NonNull;

import java.util.UUID;


@Embeddable
public record EntityId(@NonNull UUID id) {


    public EntityId {
        if (id==null) throw new IllegalArgumentException("User ID cannot be empty!");
    }

   public static EntityId generate() {
        return new EntityId(UuidCreator.getTimeOrderedEpoch());
   }
}
