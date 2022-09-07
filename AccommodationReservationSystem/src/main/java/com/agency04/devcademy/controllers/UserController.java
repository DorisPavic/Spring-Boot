package com.agency04.devcademy.controllers;

import com.agency04.devcademy.model.User;
import com.agency04.devcademy.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController{
    @Autowired
    private UserService userService;

    @Operation(tags= "Checking Users", summary = "Gives list of all users in system")
    @GetMapping("/all")
    ResponseEntity<List<User>> getAllUser() {
        log.info("Getting all accommodations");
        return ResponseEntity.ok(userService.getAllUser());
    }

    @Operation(tags= "Checking Users", summary = "Finds user by id")
    @GetMapping("/{id}")
    ResponseEntity<User> findUserById(@PathVariable String id) {
        log.info("Finding accommodation by id");
        return ResponseEntity.ok(userService.findUserById(Long.parseLong(id)));
    }

    @Operation(tags= "Saving User", summary = "Creates new user")
    @PostMapping("/add")
    ResponseEntity<User> addUser(@RequestBody @Valid User newUser) {
        log.info("Creating new user.");
        return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.CREATED);
    }

    @Operation(tags= "Saving User", summary = "Updates existing user")
    @PutMapping("/update/{id}")
    ResponseEntity<User> updateUser(@RequestBody @Valid User newUser, @PathVariable Long id) {
        log.info("Updating user.");
        return ResponseEntity.ok(userService.updateUser(newUser, id));
    }

    @Operation(tags= "Saving User", summary = "Deletes user")
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseEntity<User>> deleteUser(@PathVariable Long id) {
        log.info("Deleting user.");
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
