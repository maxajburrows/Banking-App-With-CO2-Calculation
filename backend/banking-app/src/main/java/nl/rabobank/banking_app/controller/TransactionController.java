package nl.rabobank.banking_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @GetMapping(value = "hello/{id}")
    public String helloWorld(@PathVariable("id") String id) {
        return "Hello " + id;
    }
}
