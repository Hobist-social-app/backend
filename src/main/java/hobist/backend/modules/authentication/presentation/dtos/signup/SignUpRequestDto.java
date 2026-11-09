package hobist.backend.modules.authentication.presentation.dtos.signup;

import jakarta.validation.constraints.NotBlank;

public record SignUpRequestDto(
        @NotBlank String nickname
        ,String phone
        ,@NotBlank String email
        ,@NotBlank String rawPassword) {
}
