package io.fourfinanceit.homework.services.impl;

import io.fourfinanceit.homework.dao.UserRepository;
import io.fourfinanceit.homework.model.User;
import io.fourfinanceit.homework.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by apreda on 04.07.2016.
 */
@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(User user) {
        User userFoundBySurname = userRepository.findBySurname(user.getSurname());
        User userFoundByName = userRepository.findByFirstName(user.getFirstName());
        return userFoundByName != null && userFoundBySurname != null && userFoundByName.equals(userFoundBySurname);
    }
}
