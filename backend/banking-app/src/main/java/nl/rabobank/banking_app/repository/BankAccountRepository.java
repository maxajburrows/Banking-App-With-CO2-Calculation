package nl.rabobank.banking_app.repository;

import nl.rabobank.banking_app.model.entities.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
