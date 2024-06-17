package nl.rabobank.banking_app.Service;

import java.util.List;

import nl.rabobank.banking_app.Repository.TransactionRepository;
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
        return null;
    }
}
