package hobist.backend.users.infrastructure.user;

import hobist.backend.users.domain.user.enums.StateOfAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "\"user\"")
public class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String nickname;

    private String email;

    @JoinColumn(name = "password_hash")
    private String passwordHash;

    @JoinColumn(name = "created_at")
    private Timestamp createdAt;

    @JoinColumn(name = "last_login_at")
    private Timestamp lastLoginAt;

    @JoinColumn(name = "deleted_at")
    private Timestamp deletedAt;

    @JoinColumn(name = "updated_at")
    private Timestamp updatedAt;

    @JoinColumn(name = "state_of_account")
    private StateOfAccount stateOfAccount;
}
