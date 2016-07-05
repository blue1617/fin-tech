package io.fourfinanceit.homework.dao;

import io.fourfinanceit.homework.model.Loan;
import io.fourfinanceit.homework.model.LoanApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by apreda on 03.07.2016.
 */
public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Integer> {
    List<LoanApplication> findAll();
}
