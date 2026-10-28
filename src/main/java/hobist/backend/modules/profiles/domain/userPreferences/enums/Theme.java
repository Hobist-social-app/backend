package hobist.backend.modules.profiles.domain.userPreferences.enums;

import jakarta.persistence.EnumeratedValue;

public enum Theme {
    LIGHT("light"),
    DARK("dark"),
    AUTO("auto");

    @EnumeratedValue
    private final String dbValue;

    Theme(String dbValue) {
        this.dbValue = dbValue;
    }
}
