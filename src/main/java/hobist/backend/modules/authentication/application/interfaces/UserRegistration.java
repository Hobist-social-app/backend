package hobist.backend.modules.authentication.application.interfaces;

import hobist.backend.modules.authentication.presentation.dtos.signUp.SignUpRequestDto;
import hobist.backend.modules.authentication.presentation.dtos.signUp.SignUpResponseDto;

public interface UserRegistration {

   SignUpResponseDto singUp(SignUpRequestDto dto);

}
