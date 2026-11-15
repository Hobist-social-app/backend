package hobist.backend.modules.authentication.infrastructure.security.config;

import hobist.backend.modules.authentication.application.interfaces.TokenBasedAuthenticationService;
import hobist.backend.modules.authentication.infrastructure.security.dtos.DataForTokenDto;
import hobist.backend.modules.authentication.infrastructure.security.jwt.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationConfig {

    @Bean
    public TokenBasedAuthenticationService<DataForTokenDto> authenticationService(){
        return new JwtService();
    }
}
