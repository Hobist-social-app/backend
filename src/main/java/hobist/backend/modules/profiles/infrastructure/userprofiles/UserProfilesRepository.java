package hobist.backend.modules.profiles.infrastructure.userprofiles;

import hobist.backend.modules.profiles.domain.userprofiles.UserProfiles;
import hobist.backend.shared.domain.valueobjects.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfilesRepository extends JpaRepository<UserProfiles, EntityId> {
}
