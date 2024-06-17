package nl.rabobank.banking_app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import nl.rabobank.banking_app.Service.TransactionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TransactionController.class)
class TransactionControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TransactionService service;

    @Test
    void testGetAllTransactions() throws Exception {
        when(service.listAllTransactions()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/transactions")).andExpect(status().isOk())
            .andExpect(content().json("[]"));
    }
}
