package com.github.nechai.aeroflot.dao;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import java.util.List;

public interface IUserDao {
    boolean insert(User user);
    boolean update(User user);
    boolean delete(String login);
    boolean delete (User user);
    User getUserBylogin(String login);
    List <User> getUsersByRole(Role role);
    User login(String login,String password);
}
