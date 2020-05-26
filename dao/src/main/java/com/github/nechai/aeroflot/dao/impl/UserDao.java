package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.IUserDao;
import com.github.nechai.aeroflot.dao.converter.RoleConverter;
import com.github.nechai.aeroflot.dao.converter.UserConverter;
import com.github.nechai.aeroflot.dao.entity.RoleEntity;
import com.github.nechai.aeroflot.dao.entity.UserEntity;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    private static volatile UserDao instance;

    private UserDao() {
    }

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public int save(User user) {
        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(userEntity);
        session.getTransaction().commit();
        return userEntity.getId();
    }

    @Override
    public int delete(int userId) {
        User resultUser = getUserById(userId);
        if (resultUser == null) return -1;
        resultUser.setActual(false);
        return save(resultUser);
    }

    @Override
    public int delete(User user) {
        user.setActual(false);
        return save(user);
    }

    @Override
    public User getUserByLogin(String login) {
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from UserEntity where login=:paramLogin");
        query.setParameter("paramLogin", login);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        return UserConverter.fromEntity((UserEntity) query.uniqueResult());
    }
    public User getUserById(int userId) {
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from UserEntity where id=:paramId");
        query.setParameter("paramId", userId);
        return UserConverter.fromEntity((UserEntity) query.uniqueResult());
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        RoleEntity roleEntity = RoleConverter.toEntity(role);
        List<User> users = new ArrayList<>();
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from UserEntity where role=:paramRole and actFl=:paramActFl order by lastName asc");
        query.setParameter("paramRole", roleEntity);
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List<UserEntity> userEntityList = query.list();
        for (UserEntity u : userEntityList) {
            users.add(UserConverter.fromEntity(u));
        }
        return users;
    }

    public List<User> getUsersByRole(Role role, Page page) {
        RoleEntity roleEntity = RoleConverter.toEntity(role);
        List<User> users = new ArrayList<>();
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from UserEntity where role=:paramRole and actFl=:paramActFl order by lastName asc");
        query.setParameter("paramRole", roleEntity);
        query.setParameter("paramActFl", 1);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        query.setFirstResult(page.getFirst());
        query.setMaxResults(page.getMax());
        List<UserEntity> userEntityList = query.list();
        for (UserEntity u : userEntityList) {
            users.add(UserConverter.fromEntity(u));
        }
        return users;
    }

    @Override
    public User login(String login, String password) {
        User user = getUserByLogin(login);
        if (user == null) return null;
        return user.getPassword().equals(password) ? user : null;
    }

    public int getCountOfUsers(Role role) {
        RoleEntity roleEntity = RoleConverter.toEntity(role);
        final Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select count(*) from UserEntity where actFl=:paramActFl and role=:paramRole");
        query.setParameter("paramActFl", 1);
        query.setParameter("paramRole", roleEntity);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT).
                // сущности и коллекции помечаюся как только для чтения
                        setReadOnly(true);
        long count = (Long) query.getSingleResult();
        return (int) count;
    }
}
