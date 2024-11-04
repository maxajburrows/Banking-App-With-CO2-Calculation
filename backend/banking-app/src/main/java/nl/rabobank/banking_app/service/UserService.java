package nl.rabobank.banking_app.service;

import java.util.List;

import nl.rabobank.banking_app.repository.UserRepository;
import nl.rabobank.banking_app.model.BankUser;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BankUser getUserByUsername(String userName) {
        return userRepository.findById(userName).orElse(null); // TODO: Throw exception not return null.
    }

    public BankUser createUser(BankUser user) {
        return userRepository.save(user);
    }

    public List<BankUser> getAllUsers() {
        return userRepository.findAll();
    }
}
