package com.simpleuser.repository;

import com.simpleuser.model.User;

import java.util.List;

/**
 * Repository layer for user model
 */
public interface UserDAO {
    void createUser(User user);

    User getByUserId(int id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(User user);

    void deleteUserById(int id);
}
