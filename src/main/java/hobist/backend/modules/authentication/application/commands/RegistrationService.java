package hobist.backend.modules.authentication.application.commands;

import hobist.backend.modules.authentication.application.interfaces.TokenBasedAuthenticationService;
import hobist.backend.modules.authentication.application.mapper.UserSignUpTokenMapper;
import hobist.backend.modules.authentication.presentation.dtos.signup.SignUpRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.signup.SignUpResponseDto;
import hobist.backend.modules.users.application.commands.signup.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final SignUpService signUpService;
    private final TokenBasedAuthenticationService authenticationService;
    private final UserSignUpTokenMapper mapper;

    public SignUpResponseDto singUp(SignUpRequestDto dto) {

        var serviceResponse=signUpService.signUp(dto);

        var dataForTokenDto = mapper.toDataForTokenDto(serviceResponse);

        String token =authenticationService.generateToken(dataForTokenDto);

        return new SignUpResponseDto(token);
    }
}
