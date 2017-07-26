package com.simpleuser.service;

import com.simpleuser.model.User;

import java.util.List;

/**
 * Service layer for user model
 */
public interface UserService {
    void createUser(User user) ;

    User getByUserId(int id);

    List<User> getAllUsers();

    void updateUser(User user) ;

    void deleteUser(User user);

    void deleteUserById(int id);
}
