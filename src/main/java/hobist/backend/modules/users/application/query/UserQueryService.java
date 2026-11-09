package hobist.backend.modules.users.application.query;

import hobist.backend.modules.users.domain.user.User;
import hobist.backend.modules.users.domain.user.valueobjects.Email;
import hobist.backend.modules.users.domain.user.valueobjects.Nickname;
import hobist.backend.modules.users.infrastructure.user.UserRepository;
import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import hobist.backend.shared.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserQueryService {

    // TODO: add caching functionality

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByEmailOrNickname(@NotBlank String identifier)
            throws InvalidArgumentException,ResourceNotFoundException {

        if (identifier.isBlank()) {
            throw new InvalidArgumentException("Email or nickname is required");
        }

        var user = identifier.contains("@")
                ? userRepository.findByEmail(new Email(identifier))
                : userRepository.findByNickname(new Nickname(identifier));

        return user.orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
    }
}
