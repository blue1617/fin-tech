package io.fourfinanceit.homework.services.impl;

import io.fourfinanceit.homework.dao.LoanApplicationRepository;
import io.fourfinanceit.homework.dao.LoanRepository;
import io.fourfinanceit.homework.model.ApplicationStatus;
import io.fourfinanceit.homework.model.Loan;
import io.fourfinanceit.homework.model.LoanApplication;
import io.fourfinanceit.homework.model.User;
import io.fourfinanceit.homework.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by apreda on 04.07.2016.
 */
@Component
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public boolean isValid(Loan loan, int maximumThreshold, int maximumAttemptsPerDay) {
        if (isEarlyMaxRequest(loan, maximumThreshold) || isInvalidRequestToday(loan, maximumAttemptsPerDay)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void registerApplication(String ip, ApplicationStatus applicationStatus, User user) {
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setIp(ip);
        loanApplication.setDate(LocalDate.now());
        loanApplication.setApplicationStatus(applicationStatus);
        loanApplication.setUser(user);
        loanApplicationRepository.save(loanApplication);
    }

    @Override
    public void registerLoan(Loan loan) {
        loanRepository.save(loan);
    }

    private boolean isInvalidRequestToday(Loan loanAttempt, int maximumAttemptsPerDay) {
        List<LoanApplication> loanApplicationList = loanApplicationRepository.findAll();
        List<LoanApplication> todayUserLoanApplication = loanApplicationList.stream().filter(loanApplication -> loanAttempt.getUser().getId().equals(loanApplication.getUser().getId()) && loanApplication.getDate().equals(LocalDate.now()))
                .collect(Collectors.toList());

        List<LoanApplication> allAttemptsGreater = todayUserLoanApplication.stream().filter(loanApplication -> todayUserLoanApplication.stream().filter(loanApplication1 -> !loanApplication.getId().equals(loanApplication1.getId()) &&
                loanApplication.getIp().equals(loanApplication1.getIp())).count() > maximumAttemptsPerDay).collect(Collectors.toList());
        return allAttemptsGreater != null;
    }

    private boolean isEarlyMaxRequest(Loan loan, Integer maximumThreshold) {
        return loan.getAmount() == maximumThreshold && isInSensitiveInterval(LocalTime.now());
    }

    private boolean isInSensitiveInterval(LocalTime time) {
        return time.getHour() > 0 && time.getHour() < 6;
    }
}
