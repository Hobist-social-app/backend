package hobist.backend.users.domain.user;

import hobist.backend.users.domain.user.enums.StateOfAccount;
import hobist.backend.users.domain.user.valueObjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Accessors(chain = true)//I can now chain setters on user creation
@RequiredArgsConstructor
@Table(name = "\"users\"")
public class User {

   @EmbeddedId
   @GeneratedValue
   private UserId id;

   @Embedded
   private Nickname nickname;

   @Embedded
   private Phone phone;

   @Embedded
   private Email email;

   @Embedded
   @JoinColumn(name = "password_hash")
   private PasswordHash passwordHash ;

   @JoinColumn(name = "created_at")
   @CreationTimestamp
   @Column(nullable = false, updatable = false)
   private Instant createdAt;

   @JoinColumn(name = "last_login_at")
   private Instant  lastLoginAt;

   @JoinColumn(name = "deleted_at")
   @Column(nullable = true)
   private Instant deletedAt;

   @JoinColumn(name = "updated_at")
   @Column(nullable = false)
   @UpdateTimestamp
   private Instant updateAt;

   @JoinColumn(name = "state_of_account")
   private StateOfAccount stateOfAccount;

   public User(String nickname
                             ,String phone
                             ,String email
                             ,String hash
                             ){

      this.id=UserId.generate();
      this.nickname=new Nickname(nickname);
      this.phone=new Phone(phone);
      this.email=new Email(email);
      this.passwordHash=new PasswordHash(hash);
      this.createdAt=Instant.now();
      this.lastLoginAt=Instant.now();
      this.deletedAt=null;
      this.updateAt=Instant.now();
      this.stateOfAccount=StateOfAccount.ACTIVE;
   }
}
