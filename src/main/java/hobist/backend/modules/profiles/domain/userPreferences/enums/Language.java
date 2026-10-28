package hobist.backend.modules.profiles.domain.userPreferences.enums;

import jakarta.persistence.EnumeratedValue;

public enum Language {
    ENG("eng");

    @EnumeratedValue
    private final String dbValue;

    Language(String dbValue) {
        this.dbValue = dbValue;
    }
}
