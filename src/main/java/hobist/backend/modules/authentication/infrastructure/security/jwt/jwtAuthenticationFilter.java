package hobist.backend.modules.authentication.infrastructure.security.jwt;

import hobist.backend.modules.authentication.infrastructure.security.dtos.AuthenticatedUserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class jwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException{

        final var authHeader = request.getHeader("Authorization");

        // JWT tokens are sent as "Bearer <token>"
        // If header is missing or does not start with Bearer, skips this filter
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        final var jwt = authHeader.substring(7);
        final var username= jwtService.extractNickname(jwt);
        final var userId = jwtService.extractUserId(jwt);
        final var email = jwtService.extractEmail(jwt);
        final var role=jwtService.extractRole(jwt);

        var principal= new AuthenticatedUserDto(userId,email);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            if (jwtService.isTokenValid(jwt,username)){
                var authToken = new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        List.of(new SimpleGrantedAuthority(role.toString()))
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
