package com.github.nechai.aeroflot.service.impl;
import com.github.nechai.aeroflot.dao.impl.UserDao;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.IUserService;

import java.util.List;

public class UserService implements IUserService {

    UserDao userDao = UserDao.getInstance();
    private static volatile IUserService instance;
    private void UserSevice(){
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
    public boolean addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteUser(String login) {
            return userDao.delete(login);
    }

    @Override
    public User getUser(String login) {
        return userDao.getUserBylogin(login);
    }

    @Override
    public User login(String login,String password) {
        return userDao.login(login,password);
    }

    @Override
    public List<User> getUsersOfSystem() {
        return userDao.getUsersByRole(Role.USER);
    }
}
