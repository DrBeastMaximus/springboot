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
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    //Get User By ID
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getLocationById(@PathVariable int id) {
        UserDTO user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("No user has found!", HttpStatus.NO_CONTENT);
    }

    // Create New User
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ResponseEntity<User> createLocation(@RequestBody UserDTO user) {
        User createdUser = userService.insert(user);
        if (createdUser != null) {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(createdUser, HttpStatus.BAD_REQUEST);
        }
    }

    // Modify User
    @RequestMapping(value = "/user/edit", method = RequestMethod.PUT)
    public ResponseEntity<String> editLocation(@RequestBody User user) {
        if (userService.update(user) != true) {
            return new ResponseEntity<>("User has been modified!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Modify Request Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    // Remove User
    @RequestMapping(value = "/user/remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLocationById(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>("User Removed!", HttpStatus.OK);
    }
}
