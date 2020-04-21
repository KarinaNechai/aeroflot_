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
    UserService userService= (UserService) UserService.getInstance();
    @Mock
    private UserDao userDao;
    @Test
    public void addUser() {
        Mockito.when(userDao.insert(any())).thenReturn(true);
        Mockito.when(userDao.insert(null)).thenReturn(false);
        UserService userService = (UserService) UserService.getInstance();
        User user=new User(
                "TestUserName",
                "TestUserFirstName",
                "1111111111",
                "test@test.by",
                "TestUserLogin",
                "TestUserPass",
                Role.DISPATCHER
        );
        assertTrue(userService.addUser(user));
        assertFalse(userService.addUser(null));
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
        Mockito.when(userDao.getUserBylogin("TestUserLogin")).thenReturn(user);
        assertEquals(user,userService.getUser("TestUserLogin"));
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