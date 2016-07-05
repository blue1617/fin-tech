package io.fourfinanceit.homework.dao;

import io.fourfinanceit.homework.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by apreda on 02.07.2016.
 */

@Transactional
public interface UserRepository extends CrudRepository<User, Integer>{

    User findBySurname(String surname);
    User findByFirstName(String firstName);
}
