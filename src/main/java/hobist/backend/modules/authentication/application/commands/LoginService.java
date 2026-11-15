package hobist.backend.modules.authentication.application.commands;

import hobist.backend.modules.authentication.application.interfaces.TokenBasedAuthenticationService;
import hobist.backend.modules.authentication.infrastructure.security.dtos.DataForTokenDto;
import hobist.backend.modules.authentication.presentation.dtos.login.LoginRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.login.LoginResponseDto;
import hobist.backend.modules.users.application.dtos.LoginUserDto;
import hobist.backend.modules.users.application.query.UserQueryService;
import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import hobist.backend.shared.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

    public LoginResponseDto login(@Valid @NotNull LoginRequestDto dto) throws InvalidArgumentException {
        if ( dto.identifier().isBlank()
                ||  dto.password().isBlank()) {
            throw new InvalidArgumentException("credentials are required");
        }

        LoginUserDto userDto;
        try {
            userDto = userQueryService.findByEmailOrNickname(dto.identifier());
        } catch (ResourceNotFoundException ex) {
            throw new BadCredentialsException("Invalid credentials");
        }

        if (!passwordEncoder.matches(dto.password(), userDto.password())) {
            throw new BadCredentialsException("Invalid credentials");
        }



        var dataForTokenDto = new DataForTokenDto(
                Map.of(
                        "userId", userDto.id().toString(),
                        "identifier", userDto.email(),
                        "role", userDto.role()
                ),
                userDto.nickname()
        );

        String token = authenticationService.generateToken(dataForTokenDto);

        return new LoginResponseDto(token);
    }
}
