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

    List<BankUser> bankUsers;
    List<Category> constantCategoryList;
    List<Category> randomCategoryList;

    static Random random = new Random();

    public DatabaseSeeder(UserRepository userRepository, TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository,
        CategoryRepository categoryRepository, KnownIbanRepository knownIbanRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.categoryRepository = categoryRepository;
        this.knownIbanRepository = knownIbanRepository;
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

        bankUsers = Arrays.asList(max, bob, janet);
        createCategories();
        createKnownIbans();
        createBankAccounts();
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

        constantCategoryList = Arrays.asList(rent, utilities, salary);
        randomCategoryList = Arrays.asList(groceries, clothing, transport, recreation, other);
        categoryRepository.saveAll(constantCategoryList);
        categoryRepository.saveAll(randomCategoryList);
    }

    public void createKnownIbans() {
        for (Category category : constantCategoryList) {
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

    private void createBankAccounts() {
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
        createRandomTransactions(currentAccounts);
        createConstantTransactions(currentAccounts);

    }

    public void createRandomTransactions(List<BankAccount> currentAccounts) {
        List<KnownIban> knownIbans = knownIbanRepository.findAll();
        for (BankAccount currentAccount : currentAccounts) {
            for (KnownIban knownIban : knownIbans) {
                Transaction newTransaction = new Transaction(currentAccount, knownIban.getIban(), TransactionType.SENT, BigDecimal.valueOf(random.nextDouble(250)),
                    knownIban.getCategory().getCategoryName(), knownIban.getCategory(), LocalDateTime.now());
            }
        }
    }

    private void createConstantTransactions(List<BankAccount> currentAccounts) {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < 13; i++) {
            for (BankAccount currentAccount : currentAccounts) {
                for (Category category : constantCategoryList) {
                    if (category.getCategoryName().equals("Salary")) {
                        Transaction newTransaction =
                            new Transaction(currentAccount, generateRandomIban(), TransactionType.RECEIVED, BigDecimal.valueOf(2500), category.getCategoryName(), category,
                                now.minusMonths(i));
                        transactionRepository.save(newTransaction);
                        continue;
                    } else if (category.getCategoryName().equals("Rent")) {
                        Transaction newTransaction =
                            new Transaction(currentAccount, generateRandomIban(), TransactionType.SENT, BigDecimal.valueOf(1000), category.getCategoryName(), category,
                                now.minusMonths(i));
                        transactionRepository.save(newTransaction);
                        continue;
                    }
                    Transaction newTransaction =
                        new Transaction(currentAccount, generateRandomIban(), TransactionType.SENT, BigDecimal.valueOf(200), category.getCategoryName(), category,
                            now.minusMonths(i));
                    transactionRepository.save(newTransaction);
                }
            }
        }

    }

    private void createAndSaveTransactions(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount, String description, String category) {
        Category categoryEntity = new Category(category, 0.0);
        categoryRepository.save(categoryEntity);
        Transaction sentTransaction = new Transaction(fromAccount, toAccount.getIban(), TransactionType.SENT, amount, description, categoryEntity, LocalDateTime.now());
        Transaction receivedTransaction = new Transaction(toAccount, fromAccount.getIban(), TransactionType.RECEIVED, amount, description, categoryEntity, LocalDateTime.now());
        transactionRepository.save(sentTransaction);
        transactionRepository.save(receivedTransaction);
    }

}
