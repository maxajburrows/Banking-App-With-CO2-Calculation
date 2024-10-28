package nl.rabobank.banking_app.service;

import nl.rabobank.banking_app.configuration.JwtService;
import nl.rabobank.banking_app.model.AuthenticationRequest;
import nl.rabobank.banking_app.model.AuthenticationResponse;
import nl.rabobank.banking_app.model.BankUser;
import nl.rabobank.banking_app.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails user = customUserDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }
}
