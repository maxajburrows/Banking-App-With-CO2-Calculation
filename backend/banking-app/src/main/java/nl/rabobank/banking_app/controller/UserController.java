package nl.rabobank.banking_app.controller;

import java.util.List;

import nl.rabobank.banking_app.Service.BankAccountService;
import nl.rabobank.banking_app.Service.UserService;
import nl.rabobank.banking_app.model.BankAccount;
import nl.rabobank.banking_app.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public List<BankAccount> getBankAccountsByUserId(@PathVariable Long userId) {
        return service.getAllUserAccounts(userId);
    }
}
