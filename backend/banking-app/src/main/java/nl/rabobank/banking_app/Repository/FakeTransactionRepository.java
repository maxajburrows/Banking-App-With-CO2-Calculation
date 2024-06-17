package nl.rabobank.banking_app.Repository;

import java.util.Collections;
import java.util.List;

import nl.rabobank.banking_app.model.Transaction;

import org.springframework.stereotype.Repository;

@Repository
public class FakeTransactionRepository implements TransactionRepository {
    @Override
    public List<Transaction> listAllTransactions() {
        return Collections.emptyList();
    }
}
