package hobist.backend.modules.users.infrastructure.user;

import hobist.backend.modules.users.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
