package hobist.backend.modules.authentication.application.commands;

import hobist.backend.modules.authentication.application.interfaces.UserRegistration;
import hobist.backend.modules.authentication.presentation.dtos.signUp.SignUpRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.signUp.SignUpResponseDto;
import hobist.backend.modules.users.application.commands.signUp.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService implements UserRegistration {

    private final SignUpService signUpService;

    @Override
    public SignUpResponseDto singUp(SignUpRequestDto dto) {

        var serviceResponse=signUpService.signUp(dto);



    }
}
