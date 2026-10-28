package hobist.backend.modules.users.infrastructure.user;

import hobist.backend.modules.users.domain.user.User;
import hobist.backend.shared.domain.valueObjects.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, EntityId> {
}
