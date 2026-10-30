package hobist.backend.modules.profiles.infrastructure.userProfiles;

import hobist.backend.modules.profiles.domain.userProfiles.UserProfiles;
import hobist.backend.modules.users.domain.user.User;
import hobist.backend.shared.domain.valueObjects.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfilesRepository extends JpaRepository<UserProfiles, EntityId> {
}
