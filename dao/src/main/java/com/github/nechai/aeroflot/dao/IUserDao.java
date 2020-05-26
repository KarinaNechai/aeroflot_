package com.github.nechai.aeroflot.dao;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import java.util.List;

public interface IUserDao {
    int save(User user);
    int delete(int userId);
    int delete (User user);
    User getUserByLogin(String login);
    User getUserById(int userId);
    int getCountOfUsers(Role role);
    List <User> getUsersByRole(Role role);
    List <User> getUsersByRole(Role role, Page page);
    User login(String login,String password);
}
