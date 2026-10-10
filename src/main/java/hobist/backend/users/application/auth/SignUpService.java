package hobist.backend.users.application.auth;

import hobist.backend.users.infrastructure.user.UserRepository;
import hobist.backend.users.presentation.dtos.SignUpRequestDto;
import hobist.backend.users.presentation.dtos.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpService {


    private final UserRepository userRepository;

    public SignUpResponseDto SignUp(SignUpRequestDto Dto) {
        throw new NotImplementedException();
    };

}
