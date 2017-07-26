package com.simpleuser.service;

import com.simpleuser.model.User;
import com.simpleuser.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void createUser(User user) {
        userDAO.createUser(user);
    }

    @Override
    public User getByUserId(int id) {
        return userDAO.getByUserId(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }
}
