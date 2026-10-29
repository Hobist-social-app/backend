package hobist.backend.modules.profiles.domain.userProfiles.valueObjects;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Surname(@Column(name = "surname", nullable = false, length = 255) String surname) {

    public Surname {
        if (!surname.isBlank() && !surname.matches("\\p{L}+")) {
            throw new InvalidArgumentException("Surname must contain only alphabetic characters!");
        }
        if (surname.length() > 255) {
            throw new InvalidArgumentException("Surname cannot exceed 255 characters!");
        }
    }
}
