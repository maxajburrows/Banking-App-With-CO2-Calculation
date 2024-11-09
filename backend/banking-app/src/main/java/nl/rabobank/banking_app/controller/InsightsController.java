package nl.rabobank.banking_app.controller;

import java.security.Principal;
import java.util.List;

import nl.rabobank.banking_app.dto.NewTransaction;
import nl.rabobank.banking_app.model.InsightsResponse;
import nl.rabobank.banking_app.model.entities.Category;
import nl.rabobank.banking_app.model.entities.Transaction;
import nl.rabobank.banking_app.service.InsightsService;
import nl.rabobank.banking_app.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insights")
public class InsightsController {
    private InsightsService service;
    @Autowired
    public InsightsController(InsightsService service) {
        this.service = service;
    }

    @GetMapping("/{accountNumber}")
    public InsightsResponse getCategorySpendAndCo2ByAccountNumber(@PathVariable String accountNumber) {
        return service.getCategorySpendAndCo2ByAccountNumber(accountNumber);
    }
}
