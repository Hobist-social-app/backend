package hobist.backend.modules.users.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record SignUpRequestDto(
        @NotBlank String nickname
        ,String phone
        ,@NotBlank String email
        ,@NotBlank String rawPassword) {
}
