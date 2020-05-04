package com.github.nechai.aeroflot.service;
import com.github.nechai.aeroflot.model.User;
import java.util.List;

public interface IUserService {
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(String login);
    User getUser(String login);
    User login (String login,String password);
    List <User> getUsersOfSystem();
}
