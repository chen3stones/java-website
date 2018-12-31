package com.oreo.website.service;

import com.oreo.website.dao.UserDao;
import com.oreo.website.until.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao = null;
    public boolean isExitByName(String name){
        User user = userDao.findUserByName(name);
        if(user != null)
            return true;
        else
            return false;
    }
    public boolean isExitByMail(String mail){
        User user = userDao.findUserByMail(mail);
        if(user != null)
            return true;
        else
            return false;
    }
}
