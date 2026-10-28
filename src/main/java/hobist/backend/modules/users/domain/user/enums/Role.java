package hobist.backend.modules.users.domain.user.enums;

import jakarta.persistence.EnumeratedValue;

public enum Role {
    USER("user"),
    ADMIN("admin");

    @EnumeratedValue
    private final String dbValue;

    Role(String dbValue) {
        this.dbValue = dbValue;
    }
}
