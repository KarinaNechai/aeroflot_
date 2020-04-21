package com.github.nechai.aeroflot.service;
import com.github.nechai.aeroflot.model.User;
import java.util.List;

public interface IUserService {
   boolean addUser(User user);
   boolean updateUser(User user);
    boolean deleteUser(String login);
    User getUser(String login);
    User login (String login,String password);
    List <User> getUsersOfSystem();
}
