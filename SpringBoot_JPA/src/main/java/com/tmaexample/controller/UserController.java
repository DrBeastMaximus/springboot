package com.tmaexample.controller;

import com.tmaexample.dto.UserDTO;
import com.tmaexample.entities.User;
import com.tmaexample.repos.UserRepository;
import com.tmaexample.services.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private IUserService userService;

    // Get All User
    @GetMapping(value = "/user")
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    //Get User By ID
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        UserDTO user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("No user has found!", HttpStatus.NO_CONTENT);
    }

    // Create New User
    @PostMapping(value = "/user/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
        User createdUser = userService.insert(user);
        if (createdUser != null) {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(createdUser, HttpStatus.BAD_REQUEST);
        }
    }

    // Modify User
    @PutMapping(value = "/user/edit")
    public ResponseEntity<String> editUser(@RequestBody User user) {
        if (userService.update(user) != true) {
            return new ResponseEntity<>("User has been modified!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Modify Request Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    // Remove User
    @DeleteMapping(value = "/user/remove/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>("User Removed!", HttpStatus.OK);
    }
}
