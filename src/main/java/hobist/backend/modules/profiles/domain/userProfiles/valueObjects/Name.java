package hobist.backend.modules.profiles.domain.userProfiles.valueObjects;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Name(@Column(name = "name", nullable = false, length = 255) String name) {

    public Name {
        if (!name.isBlank() && !name.matches("\\p{L}+")) {
            throw new InvalidArgumentException("Name must contain only alphabetic characters!");
        }
        if (name.length() > 255) {
            throw new InvalidArgumentException("Name cannot exceed 255 characters!");
        }
    }
}
