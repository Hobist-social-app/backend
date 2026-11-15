package hobist.backend.modules.users.domain.user;

import hobist.backend.modules.users.domain.user.Exceptions.UserBannedException;
import hobist.backend.modules.users.domain.user.Exceptions.UserDeletedException;
import hobist.backend.modules.users.domain.user.enums.Role;
import hobist.backend.modules.users.domain.user.valueobjects.Email;
import hobist.backend.modules.users.domain.user.valueobjects.Nickname;
import hobist.backend.modules.users.domain.user.valueobjects.PasswordHash;
import hobist.backend.modules.users.domain.user.valueobjects.Phone;
import hobist.backend.shared.domain.valueobjects.EntityId;
import hobist.backend.modules.users.domain.user.enums.StateOfAccount;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.TypeMismatchException;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.cache.annotation.CacheEvict;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)//M.G: this is set so hibernate can map database table to this object with java reflection
@Table(name = "\"users\"")
public class User {

   @EmbeddedId
   private EntityId id;

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
   private Instant updatedAt;

   @Enumerated(EnumType.STRING)
   @JdbcTypeCode(SqlTypes.NAMED_ENUM)//this and upper line is needed for translation of enum to postgreSQL naming convention
   @Column(name = "state_of_account",nullable = false)
   private StateOfAccount stateOfAccount;

   @Enumerated(EnumType.STRING)
   @JdbcTypeCode(SqlTypes.NAMED_ENUM)
   @Column(name= "role", nullable = false)
   private Role role;

   public User(String nickname
                             ,String phone
                             ,String email
                             ,String hash
                             ){

      this.id=EntityId.generate();
      this.nickname=new Nickname(nickname);
      this.phone=new Phone(phone);
      this.email=new Email(email);
      this.passwordHash=new PasswordHash(hash);
      this.createdAt=Instant.now();
      this.lastLoginAt=Instant.now();
      this.deletedAt=null;
      this.updatedAt =Instant.now();
      this.stateOfAccount=StateOfAccount.ACTIVE;
      this.role=Role.USER;
   }

   public void EnsureAccountIsActive() throws UserBannedException, UserDeletedException,NotImplementedException, IllegalStateException{
      switch (stateOfAccount){
         case ACTIVE -> {}
         case BANNED -> throw new UserBannedException("User is currently banned.");
         case DELETED -> throw new UserDeletedException("User account is deleted.");
         case SUSPENDED -> throw new NotImplementedException("");
         case null -> throw new IllegalStateException("User status can't be null!");
      };
   }

   // ToDo: add service for this method
   public void refreshUpdatedAtAttribute(){
      this.updatedAt=Instant.now();
   }

   // ToDo: add service for this method
   public void refreshLastLoginAtAttribute(){
      this.lastLoginAt=Instant.now();
   }

   // ToDo: add service for this method
   public void BanAccount(){
      EnsureAccountIsActive();

      this.stateOfAccount=StateOfAccount.BANNED;
      this.deletedAt=Instant.now();
      refreshUpdatedAtAttribute();
   }

   // ToDo: add service for this method
   public void UnBanAccount(){
      if (this.stateOfAccount!=StateOfAccount.BANNED){
         throw new TypeMismatchException("Account is not banned.");
      }

      this.stateOfAccount=StateOfAccount.ACTIVE;
      this.deletedAt=null;
   }

}
