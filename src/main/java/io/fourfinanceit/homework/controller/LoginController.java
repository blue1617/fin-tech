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

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = GET)
    public String index(@ModelAttribute User user, Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/processLogin", method = RequestMethod.POST)
    public String checkPersonInfo(@ModelAttribute User user, Model model, HttpSession httpSession) {
        if (loginService.isValid(user)) {
            httpSession.setAttribute("user.firstName", user.getFirstName());
            httpSession.setAttribute("user.surname", user.getSurname());
            model.addAttribute("loan", new Loan());
            return "loan";
        } else {
            model.addAttribute("validationError", "Invalid credentials!!!");
            return "login";
        }
    }
}
