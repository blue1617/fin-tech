package io.fourfinanceit.homework.dao;

import io.fourfinanceit.homework.model.Loan;
import io.fourfinanceit.homework.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by apreda on 03.07.2016.
 */
@Transactional
public interface LoanRepository extends CrudRepository<Loan, Integer> {

    List<Loan> findAll();
}