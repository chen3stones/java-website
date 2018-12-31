package com.oreo.website.controller;

import com.oreo.website.dao.UserDao;
import com.oreo.website.until.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller

public class UserController {
    @Autowired
    UserDao userDao = null;
    @ResponseBody
    @RequestMapping("/user")
    public Map<String,Object> getUser(HttpServletRequest httpServletRequest){
        int id = Integer.valueOf(httpServletRequest.getParameter("id"));
        System.out.println(id);
        User user = userDao.findUserByID(id);
        System.out.println(user.getuName());
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public Map<String,Object> test(HttpServletRequest request){
        String text = request.getParameter("text");
        System.out.println(text);
        Map<String,Object> map = new HashMap<>();
        map.put("data",text);
        return map;
    }


}
