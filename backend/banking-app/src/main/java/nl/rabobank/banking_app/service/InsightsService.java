package nl.rabobank.banking_app.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import nl.rabobank.banking_app.model.InsightsResponse;
import nl.rabobank.banking_app.model.entities.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsightsService {
    private final TransactionService transactionService;
    @Autowired
    public InsightsService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public InsightsResponse getCategorySpendAndCo2ByAccountNumber(String accountNumber) {
        List<Transaction> transactions = transactionService.listAllTransactionsByAccountNumber(accountNumber);
        Map<String, BigDecimal> categorySpend = transactions.stream()
                .collect(Collectors.toMap(transaction -> transaction.getCategory().getCategoryName(), Transaction::getAmount, BigDecimal::add));
        BigDecimal totalSpend = categorySpend.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Double> categoryCo2 = transactions.stream()
                .collect(Collectors.toMap(transaction -> transaction.getCategory().getCategoryName(), Transaction::getKgCo2, Double::sum));
        Double totalCo2 = categoryCo2.values().stream().reduce(0.0, Double::sum);
        return new InsightsResponse(categorySpend, categoryCo2, totalSpend, totalCo2);
    }
}
