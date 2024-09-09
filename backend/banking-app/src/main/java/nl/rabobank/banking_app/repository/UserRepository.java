package nl.rabobank.banking_app.repository;

import nl.rabobank.banking_app.model.BankUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BankUser, String> {
}
