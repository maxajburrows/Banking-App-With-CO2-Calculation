package nl.rabobank.banking_app.controller;

import nl.rabobank.banking_app.service.UserService;
import nl.rabobank.banking_app.model.BankUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // TODO: Protect endpoint later so only admin can access it.
    @PostMapping
    public BankUser createUser(@RequestBody BankUser user) {
        return service.createUser(user);
    }
}
