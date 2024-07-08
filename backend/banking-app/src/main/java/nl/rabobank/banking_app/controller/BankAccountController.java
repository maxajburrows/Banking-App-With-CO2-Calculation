package nl.rabobank.banking_app.controller;

import java.util.List;

import nl.rabobank.banking_app.Service.BankAccountService;
import nl.rabobank.banking_app.Service.UserService;
import nl.rabobank.banking_app.model.BankAccount;
import nl.rabobank.banking_app.model.BankUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    private BankAccountService bankAccountService;
    private String currentUserName;
    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
        this.currentUserName = "maxaj";
    }

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createBankAccount(bankAccount, currentUserName);
    }

    @GetMapping
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{accountNumber}")
    public BankAccount getBankAccountByAccountNumber(@PathVariable String accountNumber) {
        BankAccount bankAccount = bankAccountService.getBankAccountByIban(accountNumber);
        if (bankAccount == null || !bankAccount.getAccountOwner().equals(currentUserName)) {
            return null;
        }
        return bankAccount;
    }
}
