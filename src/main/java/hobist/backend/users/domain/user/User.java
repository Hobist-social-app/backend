package hobist.backend.users.domain.user;

import hobist.backend.users.domain.user.enums.StateOfAccount;
import hobist.backend.users.domain.user.valueObjects.*;

import java.sql.Timestamp;
import java.util.Optional;

public interface User {

   UserId id();

   Nickname nickname();

   Phone phone();

   Email email();

   PasswordHash passwordHash ();

   Timestamp createdAt();

   Timestamp lastLoginAt();

   Optional<Timestamp> deletedAt();

   Timestamp updateAt();

   StateOfAccount stateOfAccount();
}
