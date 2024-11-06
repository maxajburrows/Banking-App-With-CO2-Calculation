package nl.rabobank.banking_app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import jakarta.annotation.PostConstruct;

import nl.rabobank.banking_app.model.entities.BankAccount;
import nl.rabobank.banking_app.model.entities.BankUser;
import nl.rabobank.banking_app.model.entities.Category;
import nl.rabobank.banking_app.model.entities.KnownIban;
import nl.rabobank.banking_app.model.entities.Transaction;
import nl.rabobank.banking_app.model.TransactionType;
import nl.rabobank.banking_app.repository.BankAccountRepository;
import nl.rabobank.banking_app.repository.CategoryRepository;
import nl.rabobank.banking_app.repository.KnownIbanRepository;
import nl.rabobank.banking_app.repository.TransactionRepository;
import nl.rabobank.banking_app.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    private BankAccountRepository bankAccountRepository;
    private CategoryRepository categoryRepository;
    private KnownIbanRepository knownIbanRepository;

    private PasswordEncoder passwordEncoder;

    List<Category> categoryList;

    static Random random = new Random();

    public DatabaseSeeder(UserRepository userRepository, TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository,
        CategoryRepository categoryRepository, KnownIbanRepository knownIbanRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.categoryRepository = categoryRepository;
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

    public void createCategories() {
        Category rent = new Category("Rent", 0.09649);
        Category utilities = new Category("Utilities", 0.59294);

        Category groceries = new Category("Groceries", 0.61044);
        Category clothing = new Category("Clothing", 0.37203);
        Category transport = new Category("Transport", 0.2899);
        Category recreation = new Category("Recreation", 0.22464);
        Category other = new Category("Other", 0.15);

        Category savings = new Category("Savings", 0.0);
        Category withdrawal = new Category("Withdrawal", 0.0);
        Category salary = new Category("Salary", 0.0);

        categoryList = Arrays.asList(rent, groceries, clothing, transport, utilities, recreation, other, savings, withdrawal, salary);
        categoryRepository.saveAll(categoryList);
    }

    public void createKnownIbans() {
        for (Category category : categoryList) {
            for (int i = 0; i < 10; i++) {
                String iban = generateRandomIban();
                knownIbanRepository.save(new KnownIban(iban, category));
            }
        }
    }

    public static String generateRandomIban() {
        String[] bankCodes = { "ABNA", "RABO", "INGB", "BUNQ", "REVO" };
        StringBuilder iban = new StringBuilder("NL");
        iban.append(String.format("%02d", random.nextInt(100)));
        iban.append(bankCodes[random.nextInt(bankCodes.length)]);
        for (int i = 0; i < 10; i++) {
            iban.append(random.nextInt(10));
        }
        return iban.toString();
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
        List<String> categories = Arrays.asList("Groceries", "Rent", "Salary", "Gift", "Transport", "Utilities", "Other");
        for (int i = 0; i < currentAccounts.size(); i++) {
            // TODO: Can this be done with enhanced for loop?
            for (int j = 0; j < savingsAccounts.size(); j++) {
                if (i == j)
                    continue;
                String category = categories.get(random.nextInt(categories.size()));
                createAndSaveTransactions(currentAccounts.get(i), savingsAccounts.get(j), BigDecimal.valueOf(random.nextDouble(100)), category, category);
            }
        }
        for (int i = 0; i < currentAccounts.size(); i++) {
            for (int j = 0; j < currentAccounts.size(); j++) {
                if (i == j)
                    continue;
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
        Category categoryEntity = new Category(category, 0.0);
        categoryRepository.save(categoryEntity);
        Transaction sentTransaction = new Transaction(fromAccount, toAccount.getIban(), TransactionType.SENT, amount, description, categoryEntity, LocalDateTime.now());
        Transaction receivedTransaction = new Transaction(toAccount, fromAccount.getIban(), TransactionType.RECEIVED, amount, description, categoryEntity, LocalDateTime.now());
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
