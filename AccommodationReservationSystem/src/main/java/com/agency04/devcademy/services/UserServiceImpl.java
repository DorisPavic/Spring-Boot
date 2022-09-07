package com.agency04.devcademy.services;

import com.agency04.devcademy.exceptions.NotFoundException;
import com.agency04.devcademy.model.User;
import com.agency04.devcademy.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User addUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User newUser, Long id) {return userRepository.findById(id)
            .map(user -> {
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getFirstName());
                user.setEmail(newUser.getEmail());
                return userRepository.save(user);
            })
            .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public ResponseEntity<User> deleteUser(Long id) {
        User existingUser = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        this.userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
