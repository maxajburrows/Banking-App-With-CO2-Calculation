package nl.rabobank.banking_app.Service;

import java.util.List;

import nl.rabobank.banking_app.Repository.UserRepository;
import nl.rabobank.banking_app.model.BankAccount;
import nl.rabobank.banking_app.model.BankUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BankUser getUserByUserName(String userName) {
        return userRepository.findById(userName).orElse(null); // TODO: Throw exception not return null.
    }

    public BankUser createUser(final BankUser user) {
        return userRepository.save(user);
    }
}
