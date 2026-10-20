package hobist.backend.modules.users.domain.user.enums;


import jakarta.persistence.EnumeratedValue;

public enum StateOfAccount {
    ACTIVE("active"),
    SUSPENDED("suspended"),
    DELETED("deleted"),
    BANNED("banned");

    @EnumeratedValue
    private final String dbValue;

    StateOfAccount(String dbValue) {
        this.dbValue = dbValue;
    }
}
