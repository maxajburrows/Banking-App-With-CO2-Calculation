package nl.rabobank.banking_app;

import jakarta.annotation.PostConstruct;

import nl.rabobank.banking_app.Repository.BankAccountRepository;
import nl.rabobank.banking_app.Repository.TransactionRepository;
import nl.rabobank.banking_app.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;

    @PostConstruct
    public void seedDatabase() {
        loadUserData();
    }

    private void loadUserData() {
    }
}
