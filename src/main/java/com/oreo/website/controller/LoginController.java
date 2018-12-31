package com.oreo.website.controller;

import com.oreo.website.dao.CodeDao;
import com.oreo.website.dao.UserDao;
import com.oreo.website.imple.MD5;
import com.oreo.website.service.CheckMailService;
import com.oreo.website.service.UserService;
import com.oreo.website.until.Code;
import com.oreo.website.until.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
注册界面
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserDao userDao = null;
    @Autowired
    UserService userService = null;
    @Autowired
    CodeDao codeDao = null;
    @Autowired
    CheckMailService checkMailService = null;

    @RequestMapping
    public String loginIndex(){
        return "login";
    }
    /**
     * 插入用户，插入成功则返回返回true
     * 插入失败则返回false
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertUser")
    public Map<String,Object> insertUser(HttpServletRequest httpServletRequest){
        String mail = httpServletRequest.getParameter("mail");
        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");
        String MD5Password = MD5.getMD5Password(password);
        if(mail ==null || name == null || password == null){
            Map<String,Object> map = new HashMap<>();
            map.put("flag",false);
            return map;
        }

        int authority = 1;
        User user = new User();
        user.setuName(name);
        user.setuMail(mail);
        user.setuPassword(MD5Password);
        user.setAuthority(authority);
        boolean flag = false;
        boolean isExit = userService.isExitByMail(mail) || userService.isExitByName(name);
        if(!isExit){
            userDao.insert(user);
            flag = true;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("flag",flag);
        return map;
    }

    /**
     * 返回的true则表示表中存在这样的邮箱
     * 返回false表示不存在这样的邮箱
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkMail")
    public Map<String,Object> checkMail(HttpServletRequest httpServletRequest){
        String mail = httpServletRequest.getParameter("mail");
        boolean flag = false;
        User user = userDao.findUserByMail(mail);

        if(user != null){
            flag = true;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("flag",flag);
        return map;
    }

    /**
     * 返回的true表示表中存在同名的
     * 如果为false表示不存在同名
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkName")
    public Map<String,Object> checkName(HttpServletRequest httpServletRequest){
        String name = httpServletRequest.getParameter("name");
        System.out.println("login check name++"+name+"++");
        boolean flag = false;
        User user = userDao.findUserByName(name);
        System.out.println(user.getuName());
        if(user != null){
            flag = true;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("flag",flag);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getCode")
     public Map<String,Object> getCode(HttpServletRequest httpServletRequest){
        String mail = httpServletRequest.getParameter("mail");
        Map<String,Object> map = new HashMap<>();
        if(mail == null || mail == ""){
            map.put("flag",false);
            return map;
        }
        User user = userDao.findUserByMail(mail);
        Code code = codeDao.findCodeByMail(mail);
        Date now = new Date();
        if(user != null){
            map.put("flag",false);
        }else if(code == null){
            int numCode = (int)(Math.random()*10000);
            String nowCode = String.valueOf(String.format("%04d",numCode));

            System.out.println("now code is  " + nowCode);
            code = new Code();
            checkMailService.sendCode(mail,nowCode);
            code.setcMail(mail);
            code.setcCode(nowCode);
            code.setuLastTime(now);
            codeDao.insertCode(code);
            map.put("flag",true);
        }else{
            long time = Math.abs(now.getTime()-code.getuLastTime().getTime());
            int time1 = (int)time / (1000*60);
            System.out.println(time1);
            if(time1 > 1){
                int numCode = (int)(Math.random()*10000);
                String nowCode = String.valueOf(String.format("%04d",numCode));
                checkMailService.sendCode(mail,nowCode);
                code.setcCode(nowCode);
                code.setuLastTime(now);
                code.setcMail(mail);
                codeDao.updateCodeByMail(code);
                map.put("flag",true);
            }else{
                map.put("flag",false);
            }
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("submit")
    public boolean submitUser(HttpServletRequest httpServletRequest){
        String name = httpServletRequest.getParameter("name");
        String mail = httpServletRequest.getParameter("mail");
        String password = httpServletRequest.getParameter("password");
        String code = httpServletRequest.getParameter("code");
        String dbCode = codeDao.findCodeByMail(mail).getcCode();
        if(code.equals(dbCode)){
            User user = new User();
            user.setuName(name);
            user.setuPassword(MD5.getMD5Password(password));
            user.setAuthority(1);
            boolean falg = userDao.insert(user);
            return falg;
        }else {
            return false;
        }

    }

}
