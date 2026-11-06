package hobist.backend.modules.profiles.infrastructure.userpreferences;

import hobist.backend.modules.profiles.domain.userpreferences.UserPreferences;
import hobist.backend.shared.domain.valueobjects.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences,EntityId> {
}
