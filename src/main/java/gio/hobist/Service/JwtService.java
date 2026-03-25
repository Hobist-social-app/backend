package gio.hobist.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

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

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
       final Claims claims=extractAllClaims(token);
       return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(/*Map<String,Object> extraClaims,*/ UserDetails userDetails){
        return Jwts.builder()
//                .claims(extraClaims)//M.G: no roles,permissions for now
                .subject(userDetails.getUsername())//M.G: this returns email
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

   public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
   }
}
