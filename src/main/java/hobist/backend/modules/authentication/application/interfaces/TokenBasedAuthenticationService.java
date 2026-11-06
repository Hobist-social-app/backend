package hobist.backend.modules.authentication.application.interfaces;

import hobist.backend.modules.users.domain.user.enums.Role;

import java.util.UUID;

public interface TokenBasedAuthenticationService<T>{

    String generateToken(T dto);

    String extractEmail(String token);
    String extractNickname(String token);
    UUID extractUserId(String token);
    Role extractRole(String token);

    boolean isTokenValid(String token, String nickname);
}
