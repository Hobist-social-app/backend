package hobist.backend.modules.authentication.presentation.dtos.login;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(@NotNull String email, @NotNull String password) {
}
