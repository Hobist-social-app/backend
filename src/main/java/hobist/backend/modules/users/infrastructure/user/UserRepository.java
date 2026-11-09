package hobist.backend.modules.users.infrastructure.user;

import hobist.backend.modules.users.domain.user.User;
import hobist.backend.modules.users.domain.user.valueobjects.Email;
import hobist.backend.modules.users.domain.user.valueobjects.Nickname;
import hobist.backend.shared.domain.valueobjects.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, EntityId> {

    Optional<User> findByEmail(Email email);
    Optional<User> findByNickname(Nickname nickname);
}
