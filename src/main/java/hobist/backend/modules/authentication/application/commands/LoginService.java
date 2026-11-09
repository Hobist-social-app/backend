package hobist.backend.modules.authentication.application.commands;

import hobist.backend.modules.authentication.application.interfaces.TokenBasedAuthenticationService;
import hobist.backend.modules.authentication.infrastructure.security.dtos.DataForTokenDto;
import hobist.backend.modules.authentication.presentation.dtos.login.LoginRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.login.LoginResponseDto;
import hobist.backend.modules.users.application.query.UserQueryService;
import hobist.backend.modules.users.domain.user.User;
import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import hobist.backend.shared.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.AbstractValidatingPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserQueryService userQueryService;
    private final AbstractValidatingPasswordEncoder passwordEncoder;
    private final TokenBasedAuthenticationService<DataForTokenDto> authenticationService;

    public LoginResponseDto login(@Valid @NotBlank LoginRequestDto dto) throws InvalidArgumentException {
        if ( dto.email().isBlank()
                ||  dto.password().isBlank()) {
            throw new InvalidArgumentException("credentials are required");
        }

        User user;
        try {
            user = userQueryService.findByEmailOrNickname(dto.email());
        } catch (ResourceNotFoundException ex) {
            throw new BadCredentialsException("Invalid credentials");
        }

        if (!passwordEncoder.matches(dto.password(), user.getPasswordHash().password_hash())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        var dataForTokenDto = new DataForTokenDto(
                Map.of(
                        "userId", user.getId().id().toString(),
                        "email", user.getEmail().email(),
                        "role", user.getRole().toString()
                ),
                user.getNickname().nickname()
        );

        String token = authenticationService.generateToken(dataForTokenDto);

        return new LoginResponseDto(token);
    }
}
