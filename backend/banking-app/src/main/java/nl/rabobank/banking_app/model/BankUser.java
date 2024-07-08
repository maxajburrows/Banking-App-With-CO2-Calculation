package nl.rabobank.banking_app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
public class BankUser {
    @Id
    private String userName;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "accountOwner")
    @JsonIgnore
    private List<BankAccount> bankAccounts;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String fisrtName) {
        this.firstName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(final List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
