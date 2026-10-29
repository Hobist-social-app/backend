package hobist.backend.modules.profiles.domain.userProfiles.valueObjects;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Description(@Column(name = "description", length = 500) String description) {

    public Description {
        if ( description.length() > 500) {
            throw new InvalidArgumentException("Description cannot exceed 500 characters!");
        }
    }
}
