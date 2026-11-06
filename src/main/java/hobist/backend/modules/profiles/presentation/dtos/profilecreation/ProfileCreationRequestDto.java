package hobist.backend.modules.profiles.presentation.dtos.profilecreation;

import java.util.UUID;

public record ProfileCreationRequestDto(
        UUID user_id,
        String name,
        String surname,
        String description
) {
}
