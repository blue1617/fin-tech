package io.fourfinanceit.homework.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import io.fourfinanceit.homework.dao.LoanApplicationRepository;
import io.fourfinanceit.homework.dao.LoanRepository;
import io.fourfinanceit.homework.dao.UserRepository;
import io.fourfinanceit.homework.model.Loan;
import io.fourfinanceit.homework.model.User;
import io.fourfinanceit.homework.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = GET)
    public String index(Model model) {
        model.addAttribute("`user", new User());
        return "login";
    }

    @RequestMapping(value = "/processLogin", method = RequestMethod.POST)
    public String checkPersonInfo(@ModelAttribute User user, Model model) {
        if (loginService.isValid(user)) {
            Loan loan = new Loan();
            User foundUser = userRepository.findBySurname(user.getSurname());
            loan.setUser(foundUser);
            model.addAttribute("loan", loan);
            model.addAttribute("user", user);
            //todo: save user on session. cookie?
            return "loan";
        } else {
            model.addAttribute("validationError", "Invalid credentials!!!");
            return "login";
        }
    }
}
