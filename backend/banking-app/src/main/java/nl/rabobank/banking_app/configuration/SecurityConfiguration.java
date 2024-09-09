package nl.rabobank.banking_app.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
            .httpBasic(withDefaults())
            .build();
    }

    @Bean
    UserDetailsService users() {
        UserDetails max = User
            .withUsername("max@gmail.com")
            .password(passwordEncoder().encode("a123456"))
            .roles("USER", "ADMIN")
            .build();
        UserDetails bob = User
            .withUsername("bob@gmail.com")
            .password(passwordEncoder().encode("b123456"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(max, bob);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
