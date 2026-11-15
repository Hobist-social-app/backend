package hobist.backend.modules.users.application.query;

import hobist.backend.modules.users.application.dtos.LoginUserDto;
import hobist.backend.modules.users.domain.user.valueobjects.Email;
import hobist.backend.modules.users.domain.user.valueobjects.Nickname;
import hobist.backend.modules.users.infrastructure.user.UserRepository;
import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import hobist.backend.shared.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserQueryService {

    // TODO: add caching functionality

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public LoginUserDto findByEmailOrNickname(@NotBlank String identifier)
            throws InvalidArgumentException,ResourceNotFoundException {

        if (identifier.isBlank()) {
            throw new InvalidArgumentException("Email or nickname is required");
        }

        var optionalUser = identifier.contains("@")
                ? userRepository.findByEmail(new Email(identifier))
                : userRepository.findByNickname(new Nickname(identifier));



        var user=optionalUser.orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );

        user.EnsureAccountIsActive();

       return new LoginUserDto(
                user .getId().id()
               ,user.getNickname().nickname()
               ,user.getPasswordHash().password_hash()
               ,user.getEmail().email()
               ,user.getRole().toString());
    }
}
