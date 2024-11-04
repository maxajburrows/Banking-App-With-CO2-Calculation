package nl.rabobank.banking_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import nl.rabobank.banking_app.dto.NewTransaction;
import nl.rabobank.banking_app.model.BankAccount;
import nl.rabobank.banking_app.repository.TransactionRepository;
import nl.rabobank.banking_app.model.PeriodBin;
import nl.rabobank.banking_app.model.SpendingItem;
import nl.rabobank.banking_app.model.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, BankAccountService bankAccountService) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }

    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> listAllTransactionsByAccountNumber(String iban) {
        return bankAccountService.getBankAccountByIban(iban).getTransactions();
    }

    public Transaction addTransaction(NewTransaction transaction) {
        BankAccount transactionAccount = bankAccountService.getBankAccountByIban(transaction.transactionOwnerUsername());
        String category = categoriseTransaction(transaction);
        double co2Emisions = calculateCO2(transaction);

        Transaction fullTransaction = new Transaction(transaction, transactionAccount, category); // TODO: Add CO2 emisions to full transaction
        return transactionRepository.save(fullTransaction);
    }

    // TODO: Implement this in the front end!! - It is required
    public Transaction editCategory(Long transactionId, String category) {
        Optional<Transaction> transactionToUpdateOptional = transactionRepository.findById(transactionId);
        if (transactionToUpdateOptional.isEmpty()) {
            return null;
        }
        Transaction transactionToUpdate = transactionToUpdateOptional.get();
        transactionToUpdate.setCategory(category);
        return transactionRepository.save(transactionToUpdate);
    }

    public String categoriseTransaction(NewTransaction newTransaction) {
        return "Groceries"; // TODO: Implement catogorisation properly - use known IBANs (and maybe description)
    }

    public double calculateCO2(NewTransaction newTransaction) {
        return 0.0; // TODO: Implement CO2 calculation - How accurate can you make this? - Can you do subcategories?
    }

    public List<SpendingItem> calculateSpending(String accountIBAN, Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate, Optional<PeriodBin> periodBin) {
        // If no start date no filter.
        // If no end date no filter.
        // If no period bin default to week.
        // Is period bin ok for now.
        // Is there a better way to do this?
        return null;
    }
}
