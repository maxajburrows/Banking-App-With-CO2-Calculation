package nl.rabobank.banking_app.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Supplier;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import nl.rabobank.banking_app.Service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        CsrfTokenRequestHandler requestHandler = (HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> deferredCsrfToken) -> {
//            deferredCsrfToken.get();
//            new XorCsrfTokenRequestAttributeHandler().handle(request, response, deferredCsrfToken);
//        };
        return http
            .authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
            .httpBasic(withDefaults())
            .userDetailsService(userDetailsService)
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
