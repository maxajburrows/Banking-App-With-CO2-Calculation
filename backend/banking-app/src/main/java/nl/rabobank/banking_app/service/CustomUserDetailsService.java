package nl.rabobank.banking_app.service;

import java.util.Optional;

import nl.rabobank.banking_app.repository.UserRepository;
import nl.rabobank.banking_app.model.entities.BankUser;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BankUser> user = userRepository.findById(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        BankUser userObject = user.get();
        return User
            .withUsername(userObject.getUserName())
            .password(userObject.getPassword())
            .build();
    }
}
