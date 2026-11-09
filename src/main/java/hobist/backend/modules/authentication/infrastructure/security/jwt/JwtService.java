package hobist.backend.modules.authentication.infrastructure.security.jwt;

import hobist.backend.modules.authentication.application.interfaces.TokenBasedAuthenticationService;
import hobist.backend.modules.authentication.infrastructure.security.dtos.DataForTokenDto;
import hobist.backend.modules.users.domain.user.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.claims;

@Service
public class JwtService implements TokenBasedAuthenticationService<DataForTokenDto> {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final var claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token){
        return extractClaim(token,claim -> claim.get("email",String.class));
    }

    public UUID extractUserId(String token){
        var rawString = extractClaim(token,claim -> claim.get("userId", String.class));
        return UUID.fromString(rawString);
    }

    public Role extractRole(String token){
        return extractClaim(token,claims -> claims.get("role",Role.class));
    }

    public String extractNickname(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(DataForTokenDto dto) {
        return Jwts.builder()
                .claims(dto.extraClaims())
                .subject(dto.nickname())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSigningKey())
                .header().type("jwt").and()
                .compact();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, String nickname) {
        final String username = extractNickname(token);
        return (username.equals(nickname)) && !isTokenExpired(token);
    }
}
