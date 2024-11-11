package nl.rabobank.banking_app.controller;

import nl.rabobank.banking_app.model.AuthenticationRequest;
import nl.rabobank.banking_app.service.AuthenticationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest request) {
        return service.authenticate(request);
    }
}
