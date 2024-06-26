package nl.rabobank.banking_app.controller;

import java.util.List;

import nl.rabobank.banking_app.Service.TransactionService;
import nl.rabobank.banking_app.model.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private TransactionService service;
    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return service.listAllTransactions();
    }

    @GetMapping("/{accountNumber}")
    public List<Transaction> getAllTransactionsByAccountNumber(@PathVariable String accountNumber) {
        return service.listAllTransactionsByAccountNumber(accountNumber);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    @PatchMapping("/{transactionId}")
    public Transaction editCategory(@PathVariable Long transactionId, @RequestBody String category) {
        return service.editCategory(transactionId, category);
    }
}
