package hobist.backend.modules.profiles.domain.userpreferences.enums;

import jakarta.persistence.EnumeratedValue;

public enum Language {
    ENG("eng");

    @EnumeratedValue
    private final String dbValue;

    Language(String dbValue) {
        this.dbValue = dbValue;
    }
}
