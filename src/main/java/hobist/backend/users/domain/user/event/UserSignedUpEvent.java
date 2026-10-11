package hobist.backend.users.domain.user.event;

import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

public record UserSignedUpEvent(@NonNull UUID userId, @NonNull Instant signedUpAt) {
}
