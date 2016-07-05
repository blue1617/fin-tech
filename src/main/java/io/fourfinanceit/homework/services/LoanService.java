package io.fourfinanceit.homework.services;

import io.fourfinanceit.homework.model.ApplicationStatus;
import io.fourfinanceit.homework.model.Loan;
import io.fourfinanceit.homework.model.User;

/**
 * Created by apreda on 04.07.2016.
 */
public interface LoanService {
    boolean isValid(Loan loan, int maximumThreshold, int maximumAttemptsPerDay);

    void registerApplication(String ip, ApplicationStatus applicationStatus, User user);

    void registerLoan(Loan loan);
}
