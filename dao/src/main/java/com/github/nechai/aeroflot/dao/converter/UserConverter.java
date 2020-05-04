package com.github.nechai.aeroflot.dao.converter;

import com.github.nechai.aeroflot.dao.entity.UserEntity;
import com.github.nechai.aeroflot.model.User;

public class UserConverter {
    public static UserEntity toEntity(User user){
        if (user==null){
            return null;
        }
        final UserEntity userEntity=new  UserEntity();
        userEntity.setId(user.getUserId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setLogin(user.getLogin());
        userEntity.setPhone(user.getPhone());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(RoleConverter.toEntity(user.getRole()));
        userEntity.setActFl(user.isActual()?1:0);
        return userEntity;
    }
    public static User fromEntity(UserEntity userEntity){
        if (userEntity==null){
            return null;
        }
        User user=new User();
        user.setUserId(userEntity.getId());
        user.setLastName(userEntity.getLastName());
        user.setFirstName(userEntity.getLastName());
        user.setLogin(userEntity.getLogin());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setPhone(userEntity.getPhone());
        user.setRole(RoleConverter.fromEntity(userEntity.getRole()));
        user.setActual(userEntity.getActFl()==1);
        return user;
    }
}
