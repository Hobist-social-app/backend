package hobist.backend.modules.users.application.dtos;

import hobist.backend.modules.users.domain.user.enums.Role;
import lombok.NonNull;

import java.util.UUID;

public record UserSignUpServiceResponseDto(
        @NonNull UUID id
        ,@NonNull String nickname
        ,@NonNull String email
        ,@NonNull Role role) {
}
