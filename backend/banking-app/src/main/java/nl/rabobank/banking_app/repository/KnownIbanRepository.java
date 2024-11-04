package nl.rabobank.banking_app.repository;

import nl.rabobank.banking_app.model.KnownIban;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnownIbanRepository extends JpaRepository<KnownIban, String> {
}
