package nl.rabobank.banking_app.Service;

import java.util.List;
import java.util.Optional;

import nl.rabobank.banking_app.Repository.UserRepository;
import nl.rabobank.banking_app.model.BankAccount;
import nl.rabobank.banking_app.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<BankAccount> getAllUserAccounts(Long id) {
        User user = getUserById(id);
        if (user == null) {
            return null;
        }
        return user.getBankAccounts();
    }


}
