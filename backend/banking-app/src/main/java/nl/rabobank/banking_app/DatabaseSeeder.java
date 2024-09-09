package nl.rabobank.banking_app;

import jakarta.annotation.PostConstruct;

import nl.rabobank.banking_app.model.BankAccount;
import nl.rabobank.banking_app.model.BankUser;
import nl.rabobank.banking_app.repository.BankAccountRepository;
import nl.rabobank.banking_app.repository.TransactionRepository;
import nl.rabobank.banking_app.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    private BankAccountRepository bankAccountRepository;
    private PasswordEncoder passwordEncoder;

    public DatabaseSeeder(UserRepository userRepository, TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    BankUser max;
    BankUser bob;

    @PostConstruct
    public void seedDatabase() {
        createUsers();
        createBankAccounts();
        createTransactions();
    }

    private void createUsers() {
        max = new BankUser("max@gmail.com", "Max", "Mustermann", passwordEncoder.encode("m12345"));
        userRepository.save(max);
        bob = new BankUser("bob@outlook.com", "Bob", "Baumeister", passwordEncoder.encode("b12345"));
        userRepository.save(bob);
    }

    private void createBankAccounts() {
        BankAccount maxAccount1 = new BankAccount("NL01RABO1234567890", "Max's savings", max);
        BankAccount maxAccount2 = new BankAccount("NL02ABNA1234567890", "Max's current", max);
    }

    private void createTransactions() {
    }
}
