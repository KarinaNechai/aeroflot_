package com.github.nechai.aeroflot.service;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import java.util.List;

public interface IUserService {
 //   int save(User user);
    int addUser(User user);
    int updateUser(User user);
    int delete(int userId);
    User getUserById(int userId);
    User getUserByLogin(String login);
    User login (String login,String password);
    List <User> getUsersOfSystem();
    List <User> getUsersOfSystem(Page page);
    int getCountOfUsers();


}
