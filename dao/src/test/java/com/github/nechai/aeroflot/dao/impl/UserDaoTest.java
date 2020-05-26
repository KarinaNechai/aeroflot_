package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.converter.ProfessionConverter;
import com.github.nechai.aeroflot.dao.converter.RoleConverter;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDaoTest {
    public static UserDao  userDao=UserDao.getInstance();

    public static User testUser=new User(
            "TestUserName",
            "TestUserFirstName",
            "1111111111",
            "test@test.by",
            "TestUserLogin",
            "TestUserPass",
            Role.DISPATCHER
    );
    public static User testUser2=new User(
            "TestUserFirstName2",
            "TestUserName2",
            "1111111111",
            "test@test.by",
            "TestUserLogin2",
            "TestUserPass2",
            Role.DISPATCHER
    );
    @BeforeAll
    public static void init() {
        EntityManager entityManager = HibernateUtil.getEntityManager("TEST-UNIT");
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        for (Role p:Role.values() ) {
            session.saveOrUpdate(RoleConverter.toEntity(p));
        }
        session.getTransaction().commit();
        RoleConverter.init();
        int id=userDao.save(testUser);
        testUser.setUserId(id);
        id=userDao.save(testUser2);
        testUser2.setUserId(id);


    }
    @Test
    void delete() {
        assertEquals(testUser.getUserId(),userDao.delete(testUser.getUserId()));
    }

    @Test
    void testDelete() {
        assertEquals(testUser.getUserId(),userDao.delete(testUser));
    }

    @Test
    void getUserByLogin() {
        assertEquals(testUser2,userDao.getUserByLogin(testUser2.getLogin()));
    }

    @Test
    void getUserById() {
        assertEquals(testUser2,userDao.getUserById(testUser2.getUserId()));
    }

    @Test
    void login() {
        assertEquals(testUser2,userDao.login(testUser2.getLogin(),testUser2.getPassword()));
    }

    @Test
    void getCountOfUsers() {
        int i=userDao.getCountOfUsers(Role.USER);
       assertNotNull( userDao.getCountOfUsers(Role.USER));
    }
}