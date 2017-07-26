package com.simpleuser.controller;

import com.simpleuser.model.User;
import com.simpleuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CRUD interface for user model
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user)  {
        service.createUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "user_id") int id) {
        return service.getByUserId(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable(value = "user_id") int id, @RequestBody User user)  {
        user.setUserId(id);
        service.updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable(name = "user_id") int id) {
        service.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
