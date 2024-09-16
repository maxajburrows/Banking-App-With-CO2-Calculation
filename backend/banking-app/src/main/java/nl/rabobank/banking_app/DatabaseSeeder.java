package nl.rabobank.banking_app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;

import nl.rabobank.banking_app.model.BankAccount;
import nl.rabobank.banking_app.model.BankUser;
import nl.rabobank.banking_app.model.Transaction;
import nl.rabobank.banking_app.model.TransactionType;
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

    @PostConstruct
    public void seedDatabase() {
        BankUser max = new BankUser("max@gmail.com", "Max", "Mustermann", passwordEncoder.encode("m12345"));
        userRepository.save(max);
        BankUser bob = new BankUser("bob@outlook.com", "Bob", "Baumeister", passwordEncoder.encode("b12345"));
        userRepository.save(bob);
        BankUser janet = new BankUser("janet@gmail.com", "Janet", "Doe", passwordEncoder.encode("j12345"));
        userRepository.save(janet);

        List<BankUser> bankUsers = Arrays.asList(max, bob, janet);         // List<BankUser> bankUsers = userRepository.findAll();
        createBankAccounts(bankUsers);
    }


    private void createBankAccounts(List<BankUser> bankUsers) {
        BankAccount maxSavingsAccount = new BankAccount("NL01RABO1234567890", "Max's savings", bankUsers.getFirst());
        bankAccountRepository.save(maxSavingsAccount);
        BankAccount maxCurrentAccount = new BankAccount("NL02RABO1234567890", "Max's current", bankUsers.getFirst());
        bankAccountRepository.save(maxCurrentAccount);

        BankAccount bobSavingsAccount = new BankAccount("NL03INGB1234567890", "Bob's savings", bankUsers.get(1));
        bankAccountRepository.save(bobSavingsAccount);
        BankAccount bobCurrentAccount = new BankAccount("NL04RABO1234567890", "Bob's current", bankUsers.get(1));
        bankAccountRepository.save(bobCurrentAccount);

        BankAccount janetSavingsAccount = new BankAccount("NL05ABNA1234567890", "Janet's savings", bankUsers.getLast());
        bankAccountRepository.save(janetSavingsAccount);
        BankAccount janetCurrentAccount = new BankAccount("NL06RABO1234567890", "Janet's current", bankUsers.getLast());
        bankAccountRepository.save(janetCurrentAccount);

        List<BankAccount> bankAccounts = Arrays.asList(maxSavingsAccount, maxCurrentAccount, bobSavingsAccount, bobCurrentAccount, janetSavingsAccount, janetCurrentAccount);
        createTransactions(bankAccounts);
    }

    private void createTransactions(List<BankAccount> bankAccounts) {
        Transaction maxToBob1a = new Transaction(bankAccounts.get(0), bankAccounts.get(2).getIban(), TransactionType.SENT, new BigDecimal("100.00"), "Rent", "Housing", LocalDateTime.now());
        Transaction maxToBob1b = new Transaction(bankAccounts.get(2), bankAccounts.get(0).getIban(), TransactionType.RECEIVED, new BigDecimal("100.00"), "Rent", "Housing", LocalDateTime.now());
        transactionRepository.save(maxToBob1a);
        transactionRepository.save(maxToBob1b);

        Transaction bobToMax1a = new Transaction(bankAccounts.get(2), bankAccounts.get(0).getIban(), TransactionType.SENT, new BigDecimal("50.00"), "Salary", "Income", LocalDateTime.now());
        Transaction bobToMax1b = new Transaction(bankAccounts.get(0), bankAccounts.get(2).getIban(), TransactionType.RECEIVED, new BigDecimal("50.00"), "Salary", "Income", LocalDateTime.now());
        transactionRepository.save(bobToMax1a);
        transactionRepository.save(bobToMax1b);

        Transaction bobToJanet1a = new Transaction(bankAccounts.get(2), bankAccounts.get(4).getIban(), TransactionType.SENT, new BigDecimal("25.00"), "Gift", "Gift", LocalDateTime.now());
        Transaction bobToJanet1b = new Transaction(bankAccounts.get(4), bankAccounts.get(2).getIban(), TransactionType.RECEIVED, new BigDecimal("25.00"), "Gift", "Gift", LocalDateTime.now());
        transactionRepository.save(bobToJanet1a);
        transactionRepository.save(bobToJanet1b);

        Transaction janetToBob1a = new Transaction(bankAccounts.get(4), bankAccounts.get(2).getIban(), TransactionType.SENT, new BigDecimal("9.50"), "Gift", "Gift", LocalDateTime.now());
        Transaction janetToBob1b = new Transaction(bankAccounts.get(2), bankAccounts.get(4).getIban(), TransactionType.RECEIVED, new BigDecimal("9.50"), "Gift", "Gift", LocalDateTime.now());
        transactionRepository.save(janetToBob1a);
        transactionRepository.save(janetToBob1b);

        Transaction janetToMax1a = new Transaction(bankAccounts.get(4), bankAccounts.get(0).getIban(), TransactionType.SENT, new BigDecimal("15.00"), "Gift", "Gift", LocalDateTime.now());
        Transaction janetToMax1b = new Transaction(bankAccounts.get(0), bankAccounts.get(4).getIban(), TransactionType.RECEIVED, new BigDecimal("15.00"), "Gift", "Gift", LocalDateTime.now());
        transactionRepository.save(janetToMax1a);
        transactionRepository.save(janetToMax1b);

        Transaction maxToJanet1a = new Transaction(bankAccounts.get(0), bankAccounts.get(4).getIban(), TransactionType.SENT, new BigDecimal("10.00"), "Gift", "Gift", LocalDateTime.now());
        Transaction maxToJanet1b = new Transaction(bankAccounts.get(4), bankAccounts.get(0).getIban(), TransactionType.RECEIVED, new BigDecimal("10.00"), "Gift", "Gift", LocalDateTime.now());
        transactionRepository.save(maxToJanet1a);
        transactionRepository.save(maxToJanet1b);
    }
}
