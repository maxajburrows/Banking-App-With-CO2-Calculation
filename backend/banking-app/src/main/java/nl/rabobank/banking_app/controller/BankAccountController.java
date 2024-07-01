package nl.rabobank.banking_app.controller;

import java.util.List;

import nl.rabobank.banking_app.Service.BankAccountService;
import nl.rabobank.banking_app.model.BankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    private BankAccountService service;
    @Autowired
    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return service.getAllBankAccounts();
    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable String accountNumber) {
        return service.getBankAccountByIban(accountNumber);
    }
}
