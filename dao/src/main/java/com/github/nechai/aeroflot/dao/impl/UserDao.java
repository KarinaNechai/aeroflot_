package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.dao.HibernateUtil;
import com.github.nechai.aeroflot.dao.IUserDao;
import com.github.nechai.aeroflot.dao.converter.PlaneConverter;
import com.github.nechai.aeroflot.dao.converter.RoleConverter;
import com.github.nechai.aeroflot.dao.converter.UserConverter;
import com.github.nechai.aeroflot.dao.entity.PlaneEntity;
import com.github.nechai.aeroflot.dao.entity.RoleEntity;
import com.github.nechai.aeroflot.dao.entity.UserEntity;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.*;
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
        UserEntity userEntity= UserConverter.toEntity(user);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(userEntity);
        session.getTransaction().commit();
        return userEntity.getId();
    }

 /*    private int getRoleUserId (User user){
        int roleId=-1;
        try {
            String str="select t.userrole from myapp.user t where t.userlogin=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, user.getLogin() != null ? user.getLogin() : "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                roleId=rs.getInt("userrole");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return roleId;
    }*/
  /*  private int getRoleId (Role role){
        int roleId=-1;
        try {
            String str="select  t.clasid from myapp.classifier t where  t.clascode=? AND t.clasval=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            String str1=role.toString();
            ps.setString(1,"userrole");
            ps.setString(2, role.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                roleId=rs.getInt("clasid");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return roleId;
    }*/
 /*   private boolean isExist (User user){
        try {
            String str="select * from myapp.user t where t.userlogin=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, user.getLogin() != null ? user.getLogin() : "");
            ResultSet rs = ps.executeQuery();
            return (rs.next()?true:false) ;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/

/*    @Override
    public boolean update(User user) {
        if (user==null) return false;
 //       if (!isExist(user) ) return false;
        int userId=user.getUserId();
        int roleId=getRoleUserId(user);
        String str= "update myapp.user set userfirstname=?, usersurname=?,userphone=?,email=?,userlogin=?,userpassword=?,userrole=?,actfl=? where userid=?";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement rs = connection.prepareStatement(str);
            rs.setString(1,user.getFirstName() != null ? user.getFirstName() : "");
            rs.setString(2,user.getLastName() != null ? user.getLastName() : "");
            rs.setString(3,user.getPhone() != null ? user.getPhone() : "");
            rs.setString(4, user.getEmail() != null ? user.getEmail() : "");
            rs.setString(5,user.getLogin() != null ? user.getLogin() : "");
            rs.setString(6,user.getPassword() != null ? user.getPassword() : "");
            rs.setInt(7,roleId);
            rs.setInt(8,user.isActual()?1:0);
            rs.setInt(9,userId);
            if(rs.executeUpdate() == 1){
                boolean res=true;
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/

    @Override
    public int delete(String login) {
        User resultUser= getUserBylogin(login);
        if  (resultUser==null) return -1;
        resultUser.setActual(false);
        return save( resultUser);
    }

    @Override
    public int delete(User user) {
           user.setActual(false);
           return save(user);
    }

    @Override
    public User getUserBylogin(String login) {
    final Session session=HibernateUtil.getSession();
    Query query=session.createQuery("from UserEntity where login=:paramLogin");
    query.setParameter("paramLogin",login);
        query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        return UserConverter.fromEntity((UserEntity) query.uniqueResult());
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        RoleEntity roleEntity= RoleConverter.toEntity(role);
         List <User> users=new ArrayList<>();
         final Session session=HibernateUtil.getSession();
         Query query=session.createQuery("from UserEntity where role=:paramRole and actFl=:paramActFl");
         query.setParameter("paramRole",roleEntity);
        query.setParameter("paramActFl",1);
         query.setTimeout(1000).setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        List <UserEntity> userEntityList=query.list();
        for (UserEntity u:userEntityList) {
            users.add(UserConverter.fromEntity(u));
        }
        return users;
    }

    @Override
    public User login(String login, String password) {
         User user=getUserBylogin(login);
         if (user==null) return null;
         return user.getPassword().equals(password)?user:null;
    }

}
