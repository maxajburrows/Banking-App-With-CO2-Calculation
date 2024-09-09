package nl.rabobank.banking_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction editCategory(Long transactionId, String category) {
        Optional<Transaction> transactionToUpdateOptional = transactionRepository.findById(transactionId);
        if (transactionToUpdateOptional.isEmpty()) {
            return null;
        }
        Transaction transactionToUpdate = transactionToUpdateOptional.get();
        transactionToUpdate.setCategory(category);
        return transactionRepository.save(transactionToUpdate);
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
