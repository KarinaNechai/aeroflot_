package com.github.nechai.aeroflot.dao.impl;

import com.github.nechai.aeroflot.dao.DataSource;
import com.github.nechai.aeroflot.dao.IUserDao;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;

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
    public boolean insert(User user) {
       String str= "INSERT INTO myapp.user (userfirstname,usersurname,userphone,email,userlogin,userpassword,userrole) VALUES (?,?,?,?,?,?,?)";
       try {
           int roleid=getRoleId(user.getRole());
           Connection connection = DataSource.getInstance().getConnection();
           PreparedStatement ps = connection.prepareStatement(str);
           ps.setString(1, user.getFirstName() != null ? user.getFirstName() : "");
           ps.setString(2, user.getLastName() != null ? user.getLastName() : "");
           ps.setString(3, user.getPhone() != null ? user.getPhone() : "");
           ps.setString(4, user.getEmail() != null ? user.getEmail() : "");
           ps.setString(5, user.getLogin() != null ? user.getLogin() : "");
           ps.setString(6,user.getPassword() != null ? user.getPassword() : "");
           ps.setInt(7, roleid);
           if(ps.executeUpdate() == 1){
               boolean res=true;
               return true;
           }
           else{
               return false;
           }
       }catch (SQLException e)
       {throw new RuntimeException(e);
       }
    }

    private int getUserid (User user){
        int userId=-1;
        try {
            String str="select t.userid from myapp.user t where t.userlogin=?";
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, user.getLogin() != null ? user.getLogin() : "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userId=rs.getInt("userid");
                 }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return userId;
    }
    private int getRoleUserId (User user){
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
    }
    private int getRoleId (Role role){
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
    }
    private boolean isExist (User user){
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
    }

    @Override
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
    }

    @Override
    public boolean delete(String login) {
        User resultUser= getUserBylogin(login);
        if  (resultUser==null) return false;
        resultUser.setActual(false);
        return update( resultUser);
    }

       @Override
    public boolean delete(User user) {
        boolean result;
        int userId = getUserid(user);
        String str = "delete from myapp.user where userid=?";
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement rs = connection.prepareStatement(str);
            rs.setInt(1, userId);
            result = (rs.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public User getUserBylogin(String login) {
    try {
        Connection connection = DataSource.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement("select t.*,c.clasval from myapp.user t, myapp.classifier c where t.userrole=c.clasid and t.userlogin=?");
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        if (rs.next())  {
            String q=rs.getString("clasval");
             Role role=Role.valueOf(q);
              final User user = new User(
                        rs.getInt("userid"),
                        rs.getString("userfirstname"),
                        rs.getString("usersurname"),
                        rs.getString("userphone"),
                        rs.getString("email"),
                        rs.getString("userlogin"),
                        rs.getString("userpassword"),
                        Role.valueOf(rs.getString("clasval")),
                        rs.getInt("actfl")==1?true:false            );
              return user;
        }
        else {
            return null;
        }
    }catch (SQLException e){
        throw new RuntimeException(e);
    }
    }

    @Override
    public List<User> getUsersByRole(Role role) {
       int roleId=getRoleId(role);
       List <User> users=new ArrayList<>();
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(" select t.*,c.clasval from myapp.user t," +
                    " myapp.classifier c where  (t.userrole=c.clasid and t.userrole=?) and t.actfl=1");
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final User user = new User(
                        rs.getString("userfirstname"),
                        rs.getString("usersurname"),
                        rs.getString("userphone"),
                        rs.getString("email"),
                        rs.getString("userlogin"),
                        rs.getString("userpassword"),
                        Role.valueOf(rs.getString("clasval")));
                users.add(user);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
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
