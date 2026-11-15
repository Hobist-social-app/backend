package hobist.backend.modules.authentication.presentation.dtos.login;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record LoginRequestDto(@NotNull String identifier, @NotNull String password){
}
