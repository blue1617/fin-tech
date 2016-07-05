package io.fourfinanceit.homework.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.GenerationType.IDENTITY;
/**
 * Created by apreda on 02.07.2016.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Integer id;
    @Column(name = "FIRST_NAME", unique = true, nullable = false, length = 20)
    private String firstName;
    @Column(name = "SURNAME", unique = true, nullable = false)
    private String surname;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Loan> loans = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<LoanApplication> loanApplications = new HashSet<>();

    public User() {
    }

    public User(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public Set<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(Set<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", surname=" + surname + "]";
    }
}
