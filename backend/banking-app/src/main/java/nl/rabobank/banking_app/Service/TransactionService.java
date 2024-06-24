package nl.rabobank.banking_app.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import nl.rabobank.banking_app.Repository.TransactionRepository;
import nl.rabobank.banking_app.model.PeriodBin;
import nl.rabobank.banking_app.model.SpendingItem;
import nl.rabobank.banking_app.model.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> listAllTransactions() {
        return transactionRepository.listAllTransactions();
    }

    public List<Transaction> listAllTransactionsByAccountNumber(String accountNumber) {
        return transactionRepository.listAllTransactions().stream()
                .filter(transaction -> transaction.getFromIban().equals(accountNumber) || transaction.getToIban().equals(accountNumber))
                .toList();
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.addTransaction(transaction);
    }

    public String editCategory(String transactionId, String category) {
        Transaction updatedTransaction;
        for (Transaction transaction : transactionRepository.listAllTransactions()) {
            if (transaction.getTransactionId().equals(transactionId)) {
                updatedTransaction = transaction;
                break;
            }
        }
        // TODO: Finish implementing this
        //transactionRepository.
        return null;
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
