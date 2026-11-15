package hobist.backend.modules.authentication.application.interfaces;


import java.util.UUID;

public interface TokenBasedAuthenticationService<T>{

    String generateToken(T dto);

    String extractEmail(String token);
    String extractNickname(String token);
    UUID extractUserId(String token);
    String extractRole(String token);

    boolean isTokenValid(String token, String nickname);
}
