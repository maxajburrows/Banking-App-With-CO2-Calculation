package nl.rabobank.banking_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import nl.rabobank.banking_app.dto.NewTransaction;
import nl.rabobank.banking_app.model.entities.BankAccount;
import nl.rabobank.banking_app.model.entities.Category;
import nl.rabobank.banking_app.model.entities.KnownIban;
import nl.rabobank.banking_app.repository.CategoryRepository;
import nl.rabobank.banking_app.repository.KnownIbanRepository;
import nl.rabobank.banking_app.repository.TransactionRepository;
import nl.rabobank.banking_app.model.entities.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;
    private final KnownIbanRepository knownIbanRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, BankAccountService bankAccountService, KnownIbanRepository knownIbanRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
        this.knownIbanRepository = knownIbanRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> listAllTransactionsByAccountNumber(String iban) {
        List<Transaction> transactions = bankAccountService.getBankAccountByIban(iban).getTransactions();
        transactions.sort((t1, t2) -> t2.getTransactionDateTime().compareTo(t1.getTransactionDateTime()));
        return transactions;
    }

    public Transaction addTransaction(NewTransaction transaction) {
        BankAccount transactionAccount = bankAccountService.getBankAccountByIban(transaction.transactionOwnerIban());
        Category category = categoriseTransaction(transaction);

        Transaction fullTransaction = new Transaction(transaction, transactionAccount, category);
        return transactionRepository.save(fullTransaction);
    }

    // TODO: Implement this in the front end!! - It is required
    public Transaction editCategory(Long transactionId, Category category) {
        Optional<Transaction> transactionToUpdateOptional = transactionRepository.findById(transactionId);
        if (transactionToUpdateOptional.isEmpty()) {
            return null;
        }
        Transaction transactionToUpdate = transactionToUpdateOptional.get();
        transactionToUpdate.setCategory(category);
        return transactionRepository.save(transactionToUpdate);
    }

    public Category categoriseTransaction(NewTransaction newTransaction) {
        Optional<KnownIban> knownIban = knownIbanRepository.findById(newTransaction.toBankAccount());
        if (knownIban.isPresent()) {
            return knownIban.get().getCategory();
        }
        return categoryRepository.findById("Other").get();
    }
}
