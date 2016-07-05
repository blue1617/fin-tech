package io.fourfinanceit.homework;

import io.fourfinanceit.homework.dao.LoanApplicationRepository;
import io.fourfinanceit.homework.dao.LoanRepository;
import io.fourfinanceit.homework.dao.UserRepository;
import io.fourfinanceit.homework.model.Loan;
import io.fourfinanceit.homework.model.LoanApplication;
import io.fourfinanceit.homework.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
@EnableJpaRepositories("io.fourfinanceit.homework.dao")
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        userRepository.save(new User("Jack", "Bauer"));
        userRepository.save(new User("Chloe", "O'Brian"));
        userRepository.save(new User("Kim", "Lopez"));
        userRepository.save(new User("David", "Palmer"));
        userRepository.save(new User("Michelle", "Dessler"));

        //fetch an individual customer by ID
        User user = userRepository.findOne(1);

        Loan loan = new Loan();
        loan.setTerm(12);
        loan.setAmount(100);
        loan.setUser(user);//todo: save this in the other repository
        loanRepository.save(loan);

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setIp("127.0.0.1");
        loanApplication.setDate(LocalDate.now());
        loanApplication.setUser(user);
        loanApplicationRepository.save(loanApplication);
    }
}
