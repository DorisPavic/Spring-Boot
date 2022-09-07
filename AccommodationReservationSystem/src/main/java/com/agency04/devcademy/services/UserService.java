package com.agency04.devcademy.services;

import com.agency04.devcademy.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User findUserById(Long id);

    List<User> getAllUser();

    User addUser(User newUser);

    User updateUser(User newUser, Long id);

    ResponseEntity<User> deleteUser(Long id);
}
