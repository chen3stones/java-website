package com.oreo.website.controller;

import com.oreo.website.dao.UserDao;
import com.oreo.website.imple.MD5;
import com.oreo.website.until.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆页面
 */
@Controller
@RequestMapping("/sign")
public class SigninController {
    @Autowired
    UserDao userDao = null;
    @Autowired
    MD5 toBeMD5 = null;

    //@ResponseBody
    @RequestMapping
    public String  testModview(){
        return "sign";
    }

    /**
     * 返回1表示已数据库中已经存在这个邮箱，非法
     * 返回0表示数据库中没有该邮箱，合法
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkMail",method = RequestMethod.POST)
    public Map<String,Object> chechMail(HttpServletRequest httpServletRequest){
        String mail = httpServletRequest.getParameter("mail");
        if(mail == null || mail == ""){
            Map<String,Object> map = new HashMap<>();
            map.put("flag",1);
            return map;
        }
        System.out.println("检查邮箱++" + mail+"++是否存在于数据库中");
        User user = userDao.findUserByMail(mail);
        Map<String,Object> map = new HashMap<>();
        if (user != null){
            map.put("flag",true);
        }
        else{
            map.put("flag",false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public Map<String,Object> checkUser(HttpServletRequest httpServletRequest){
        System.out.println("check");
        String mail = httpServletRequest.getParameter("mail");
        String password = httpServletRequest.getParameter("password");
        System.out.println("检查邮箱为++"+mail+"++的用户密码是否匹配");
        boolean flag = false;
        try{
            String MD5Password = MD5.getMD5Password(password);
            String dataPassword = userDao.findPasswordByMail(mail);
            flag = MD5.checkPassword(dataPassword,MD5Password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("flag",flag);
        return map;
    }
}
