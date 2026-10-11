package hobist.backend.users.application.auth;

import hobist.backend.users.domain.user.User;
import hobist.backend.users.domain.user.event.UserSignedUpEvent;
import hobist.backend.users.domain.user.valueObjects.UserId;
import hobist.backend.users.infrastructure.user.UserRepository;
import hobist.backend.users.presentation.dtos.SignUpRequestDto;
import hobist.backend.users.presentation.dtos.SignUpResponseDto;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    public SignUpResponseDto SignUp(@NonNull SignUpRequestDto dto) {

       throw new NotImplementedException("Unfinished method!");

        var object=  Try.of(() ->
                User.Create(dto.nickname(),dto.phone(),dto.email()))
                .map(o -> o.setId(new UserId(UUID.randomUUID()))
                );

        /* hashing the password*/

        if (object.isFailure()) {
            Throwable ex=object.failed().get();

            if (ex instanceof IllegalArgumentException) {
               throw (IllegalArgumentException) ex;
            }
            else if (ex instanceof NullPointerException) {
                throw (NullPointerException) ex;
            }
            else if (ex instanceof RuntimeException){
                throw (RuntimeException) ex;
            }
        }

        var user=object.get();


       userRepository.save(user);

       eventPublisher.publishEvent(new UserSignedUpEvent(user.getId().id(), Instant.now()));
            //M.G: add listener class later
    };

}
