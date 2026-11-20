package hobist.backend.modules.authentication.application.commands;

import hobist.backend.modules.authentication.application.interfaces.TokenBasedAuthenticationService;
import hobist.backend.modules.authentication.application.mapper.UserSignUpTokenMapper;
import hobist.backend.modules.users.application.dtos.SignUpRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.signup.SignUpResponseDto;
import hobist.backend.modules.users.application.commands.signup.UserCreationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final UserCreationService userCreationService;
    private final TokenBasedAuthenticationService authenticationService;
    private final UserSignUpTokenMapper mapper;

    public SignUpResponseDto singUp(@Valid @NotBlank SignUpRequestDto dto) {

        var serviceResponse= userCreationService.signUp(dto);

        var dataForTokenDto = mapper.toDataForTokenDto(serviceResponse);

        String token =authenticationService.generateToken(dataForTokenDto);

        return new SignUpResponseDto(token);
    }
}
