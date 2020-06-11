package com.github.nechai.aeroflot.service.impl;
import com.github.nechai.aeroflot.dao.impl.UserDao;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    private UserDao userDao;
    @Test
    public void addUser() {
        Mockito.when(userDao.save(any())).thenReturn(1);
        Mockito.when(userDao.save(null)).thenReturn(-1);
               User user=new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                "TestUserLogin",
                "TestUserPass",
                Role.DISPATCHER
        );
        assertEquals(1,userService.addUser(user));
        assertEquals(-1,userService.addUser(null));
    }

    @Test
    public void getUser() {
        User user=new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                "TestUserLogin",
                "TestUserPass",
                Role.DISPATCHER
        );
        Mockito.when(userDao.getUserById(1)).thenReturn(user);
        assertEquals(user,userService.getUserById(1));
      }

    @Test
    void login() {
        User user=new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                "TestUserLogin",
                "TestUserPass",
                Role.DISPATCHER
        );
        Mockito.when(userDao.login("TestUserLogin","TestUserPass")).thenReturn(user);
        assertEquals(user,userService.login("TestUserLogin","TestUserPass"));
    }
}
