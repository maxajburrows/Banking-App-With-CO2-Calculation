package nl.rabobank.banking_app.Repository;

import java.util.List;

import nl.rabobank.banking_app.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
