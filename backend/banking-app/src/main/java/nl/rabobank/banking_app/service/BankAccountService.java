package nl.rabobank.banking_app.service;

import java.util.List;

import nl.rabobank.banking_app.repository.BankAccountRepository;
import nl.rabobank.banking_app.model.BankAccount;
import nl.rabobank.banking_app.model.BankUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepository repo;
    private final UserService userService;
    @Autowired
    public BankAccountService(BankAccountRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    public List<BankAccount> getAllBankAccounts() {
        return repo.findAll();
    }

    public BankAccount getBankAccountByIban(String iban) {
        return repo.findById(iban).orElse(null);
    }

    public BankAccount createBankAccount(BankAccount newBankAccount, String currentUserName) {
        BankUser currentUser = userService.getUserByUserName(currentUserName);
        newBankAccount.setAccountOwner(currentUser);
        return repo.save(newBankAccount);
    }
}
