package nl.rabobank.banking_app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        System.out.println(bankUsers.get(1).getUserName());
        bankAccountRepository.save(bobSavingsAccount);
        BankAccount bobCurrentAccount = new BankAccount("NL04RABO1234567890", "Bob's current", bankUsers.get(1));
        System.out.println(bankUsers.get(1).getUserName());
        bankAccountRepository.save(bobCurrentAccount);

        BankAccount janetSavingsAccount = new BankAccount("NL05ABNA1234567890", "Janet's savings", bankUsers.getLast());
        bankAccountRepository.save(janetSavingsAccount);
        BankAccount janetCurrentAccount = new BankAccount("NL06RABO1234567890", "Janet's current", bankUsers.getLast());
        bankAccountRepository.save(janetCurrentAccount);

        List<BankAccount> currentAccounts = Arrays.asList(maxCurrentAccount, bobCurrentAccount, janetCurrentAccount);
        List<BankAccount> savingsAccounts = Arrays.asList(maxSavingsAccount, bobSavingsAccount, janetSavingsAccount);
        createTransactions(currentAccounts, savingsAccounts);
    }

    private void createTransactions(List<BankAccount> currentAccounts, List<BankAccount> savingsAccounts) {
        Random random = new Random();
        List<String> categories = Arrays.asList("Groceries", "Rent", "Salary", "Gift", "Other");
        for (int i = 0; i < currentAccounts.size(); i++) {
            // TODO: Can this be done with enhanced for loop?
            for (int j = 0; j < savingsAccounts.size(); j++) {
                if (i == j) continue;
                String category = categories.get(random.nextInt(categories.size()));
                createAndSaveTransactions(currentAccounts.get(i), savingsAccounts.get(j), BigDecimal.valueOf(random.nextDouble(100)), category, category);
            }
        }
        for (int i = 0; i < currentAccounts.size(); i++) {
            for (int j = 0; j < currentAccounts.size(); j++) {
                if (i == j) continue;
                String category = categories.get(random.nextInt(categories.size()));
                createAndSaveTransactions(currentAccounts.get(j), currentAccounts.get(i), BigDecimal.valueOf(random.nextDouble(100)), category, category);
            }
        }
        for (int i = 0; i < currentAccounts.size(); i++) {
            for (int j = 0; j < 10; j++) {
                createAndSaveTransactions(currentAccounts.get(i), savingsAccounts.get(i), BigDecimal.valueOf(random.nextInt(100)), "Savings", "Savings");
                createAndSaveTransactions(savingsAccounts.get(i), currentAccounts.get(i), BigDecimal.valueOf(random.nextInt(100)), "Emergency withdrawal", "Withdrawal");
            }
        }

//        calculateBalances(currentAccounts);
//        calculateBalances(savingsAccounts);
    }

    private void createAndSaveTransactions(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount, String description, String category) {
        Transaction sentTransaction = new Transaction(fromAccount, toAccount.getIban(), TransactionType.SENT, amount, description, category, LocalDateTime.now());
        Transaction receivedTransaction = new Transaction(toAccount, fromAccount.getIban(), TransactionType.RECEIVED, amount, description, category, LocalDateTime.now());
        transactionRepository.save(sentTransaction);
        transactionRepository.save(receivedTransaction);
    }

//    private void calculateBalances(List<BankAccount> bankAccounts) {
//        for (BankAccount bankAccount : bankAccounts) {
//            bankAccount.calculateBalance();
//            bankAccountRepository.save(bankAccount);
//        }
//    }
}
