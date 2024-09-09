package nl.rabobank.banking_app;

import jakarta.annotation.PostConstruct;

import nl.rabobank.banking_app.repository.BankAccountRepository;
import nl.rabobank.banking_app.repository.TransactionRepository;
import nl.rabobank.banking_app.repository.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    private BankAccountRepository bankAccountRepository;

    public DatabaseSeeder(UserRepository userRepository, TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @PostConstruct
    public void seedDatabase() {
        loadUserData();
    }

    private void loadUserData() {
    }
}
