package com.simpleuser.repository;

import com.simpleuser.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class UserImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getByUserId(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void deleteUserById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(currentSession.get(User.class, id));
    }
}
