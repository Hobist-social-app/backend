package hobist.backend.modules.users.application.commands.signup;

import hobist.backend.modules.users.application.dtos.UserSignUpServiceResponseDto;
import hobist.backend.modules.users.domain.user.User;
import hobist.backend.modules.users.domain.user.events.UserSignedUpEvent;
import hobist.backend.modules.users.infrastructure.user.UserRepository;
import hobist.backend.modules.users.application.dtos.SignUpRequestDto;
import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.AbstractValidatingPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;


@RequiredArgsConstructor
@Service
public class UserCreationService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final AbstractValidatingPasswordEncoder passwordEncoder;

    @Transactional
    public UserSignUpServiceResponseDto signUp(@Valid @NotNull SignUpRequestDto dto) throws InvalidArgumentException {


        if ( dto.rawPassword()==null || dto.rawPassword().isBlank()){
            throw new InvalidArgumentException("Password is required!");
        }
       var hash=passwordEncoder.encode(dto.rawPassword());

        var user=new User(
                dto.nickname()
                ,dto.phone()
                ,dto.email()
                ,hash);


        userRepository.save(user);

       eventPublisher.publishEvent(new UserSignedUpEvent(user.getId().id(), Instant.now()));

        return new UserSignUpServiceResponseDto(user.getId().id()
                ,user.getNickname().nickname()
                ,user.getEmail().email()
                ,user.getRole().toString());
    };

}
