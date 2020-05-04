package com.github.nechai.aeroflot.dao;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import java.util.List;

public interface IUserDao {
    int save(User user);
 //   int update(User user);
    int delete(String login);
    int delete (User user);
    User getUserBylogin(String login);
    List <User> getUsersByRole(Role role);
    User login(String login,String password);
}
