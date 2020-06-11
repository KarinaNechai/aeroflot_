package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.IUserDao;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserService implements IUserService {

    IUserDao userDao ;

    public UserService(IUserDao userDao) {
        this.userDao=userDao;
    }

    @Override
    @Transactional
    public int addUser(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public int updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public int delete(int userId) {
        return userDao.delete(userId);
    }

    @Override
    @Transactional
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    @Transactional
    public User login(String login, String password) {
        return userDao.login(login, password);
    }

    @Override
    @Transactional
    public List<User> getUsersOfSystem() {
        return userDao.getUsersByRole(Role.USER);
    }

    @Override
    @Transactional
    public int getCountOfUsers() {
        return userDao.getCountOfUsers(Role.USER);
    }

    @Override
    @Transactional
    public List<User> getUsersOfSystem(Page page) {
        return userDao.getUsersByRole(Role.USER, page);
    }
}
