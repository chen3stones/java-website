package com.oreo.website.dao;

import com.oreo.website.until.User;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {
    List<User> findAllUser();
    User findUserByID(int id);
    User findUserByMail(String mail);
    User findUserByName(String name);
    String findPasswordByMail(String mail);
    boolean setNameByID(int ID,String name);
    boolean setNameByMail(String name,String mail);
    boolean setPasswordByMail(String mail,String password);
    boolean setAuthorityByID(int id,int authority);
    boolean setAuthorityByMail(String mail,int authority);
    boolean insert(User user1);
    boolean deleteByMail(String mail);
    boolean deleteByName(String name);
}
