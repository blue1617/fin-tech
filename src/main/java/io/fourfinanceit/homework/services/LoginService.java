package io.fourfinanceit.homework.services;

import io.fourfinanceit.homework.model.User;

/**
 * Created by apreda on 04.07.2016.
 */

public interface LoginService {
    boolean isValid(User user);
}
