package hobist.backend.modules.users.infrastructure.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.AbstractValidatingPasswordEncoder;
import org.springframework.security.crypto.password4j.Argon2Password4jPasswordEncoder;

@Configuration
public class PasswordHashingConfig {

    @Bean
    public AbstractValidatingPasswordEncoder passwordEncoder() {
        return new Argon2Password4jPasswordEncoder();
    }

}
