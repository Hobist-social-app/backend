package hobist.backend.users.domain.user;

import hobist.backend.users.domain.user.enums.StateOfAccount;
import hobist.backend.users.domain.user.valueObjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;

@Entity
@Getter
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
   @Column(name = "password_hash")
   private PasswordHash passwordHash ;

   @CreationTimestamp
   @Column(name = "created_at",nullable = false, updatable = false)
   private Instant createdAt;

   @Column(name = "last_login_at")
   private Instant  lastLoginAt;

   @Column(name = "deleted_at",nullable = true)
   private Instant deletedAt;

   @Column(name = "updated_at",nullable = false)
   @UpdateTimestamp
   private Instant updateAt;

   @Enumerated(EnumType.STRING)
   @JdbcTypeCode(SqlTypes.NAMED_ENUM)//this and upper line is needed for translation of enum to postgreSQL naming convention
   @Column(name = "state_of_account",nullable = false)
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
