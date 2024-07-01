package nl.rabobank.banking_app.Service;

import java.util.List;

import nl.rabobank.banking_app.Repository.BankAccountRepository;
import nl.rabobank.banking_app.Repository.TransactionRepository;
import nl.rabobank.banking_app.model.BankAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepository repo;
    @Autowired
    public BankAccountService(BankAccountRepository repo) {
        this.repo = repo;
    }

    public List<BankAccount> getAllBankAccounts() {
        return repo.findAll();
    }

    public BankAccount getBankAccountByIban(String iban) {
        return repo.findById(iban).orElse(null);
    }
}
