package hobist.backend.modules.profiles.domain.userPreferences.enums;

import jakarta.persistence.EnumeratedValue;

public enum OnlineVisibility {
    VISIBLE("visible"),
    NOT_VISIBLE("not_visible");

    @EnumeratedValue
    private final String dbValue;

    OnlineVisibility(String dbValue) {
        this.dbValue = dbValue;
    }
}
