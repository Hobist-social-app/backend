package hobist.backend.modules.authentication.presentation.dtos.login;

import lombok.NonNull;

public record LoginRequestDto(@NonNull String email,@NonNull String password) {
}
