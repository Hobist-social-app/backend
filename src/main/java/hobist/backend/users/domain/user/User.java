package hobist.backend.users.domain.user;

import hobist.backend.users.domain.user.valueObject.Nickname;
import hobist.backend.users.domain.user.valueObject.UserId;

public interface User {

   UserId id();

   Nickname nickname();


}
