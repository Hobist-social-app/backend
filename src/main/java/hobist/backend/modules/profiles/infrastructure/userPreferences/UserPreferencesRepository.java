package hobist.backend.modules.profiles.infrastructure.userPreferences;

import hobist.backend.modules.profiles.domain.userPreferences.UserPreferences;
import hobist.backend.shared.domain.valueObjects.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences,EntityId> {
}
