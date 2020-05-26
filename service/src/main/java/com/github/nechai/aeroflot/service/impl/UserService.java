package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.UserDao;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.IUserService;

import java.util.List;

public class UserService implements IUserService {

    UserDao userDao = UserDao.getInstance();
    private static volatile IUserService instance;

    private void UserSevice() {
    }

    public static IUserService getInstance() {
        IUserService localInstance = instance;
        if (localInstance == null) {
            synchronized (IUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int addUser(User user) {
        return userDao.save(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public int delete(int userId) {
        return userDao.delete(userId);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public User login(String login, String password) {
        return userDao.login(login, password);
    }

    @Override
    public List<User> getUsersOfSystem() {
        return userDao.getUsersByRole(Role.USER);
    }

    @Override
    public int getCountOfUsers() {
        return userDao.getCountOfUsers(Role.USER);
    }

    @Override
    public List<User> getUsersOfSystem(Page page) {
        return userDao.getUsersByRole(Role.USER, page);
    }
}
