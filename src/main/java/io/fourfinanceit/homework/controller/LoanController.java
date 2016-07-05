package io.fourfinanceit.homework.controller;

import io.fourfinanceit.homework.dao.LoanApplicationRepository;
import io.fourfinanceit.homework.dao.LoanRepository;
import io.fourfinanceit.homework.dao.UserRepository;
import io.fourfinanceit.homework.model.ApplicationStatus;
import io.fourfinanceit.homework.model.Loan;
import io.fourfinanceit.homework.model.User;
import io.fourfinanceit.homework.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by apreda on 03.07.2016.
 */
@Controller
public class LoanController {

    private static final int MAXIMUM_ATTEMPTS_PER_DAY = 2;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Value("${loan.threshold}")
    public static Integer maximumThreshold;
    @Autowired
    private LoanService loanService;

    @RequestMapping(value = "/processLoan", method = RequestMethod.POST)
    public String checkLoan(@ModelAttribute Loan loan, @ModelAttribute User user, Model model, HttpServletRequest httpServletRequest) {
        if (loanService.isValid(loan, maximumThreshold, MAXIMUM_ATTEMPTS_PER_DAY)) {
            model.addAttribute("loan", loan);
            String ip = httpServletRequest.getHeader("X-FORWARDED-FOR");
            loanService.registerApplication(ip, ApplicationStatus.SUCCESS, loan.getUser());
            loanService.registerLoan(loan);
            return "confirmation";
        } else {
            String ip="12222";
            loanService.registerApplication(ip, ApplicationStatus.FAILURE, loan.getUser());
            model.addAttribute("validationError", "Invalid credentials!!!");
            return "loan";
        }

    }

}
