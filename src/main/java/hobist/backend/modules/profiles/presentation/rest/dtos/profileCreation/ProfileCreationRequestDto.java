package hobist.backend.modules.profiles.presentation.rest.dtos.profileCreation;

import java.util.UUID;

public record ProfileCreationRequestDto(
        UUID user_id,
        String name,
        String surname,
        String description
) {
}
