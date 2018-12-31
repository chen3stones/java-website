package com.oreo.website.imple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class MD5 {

    static String SALT = "1234567890!@#$%^&*()";

    /**
     * 输入密码，加盐后返回MD5后的字符串
     * @param password
     * @return
     * @throws Exception
     */
    public static String getMD5Password(String password){
        password = password + SALT;
        String MD5String = DigestUtils.md5DigestAsHex(password.getBytes());
        return MD5String;
    }

    /**
     * 检查输入的密码和数据库中的密码是否相等
     * 相等返回true，否则返回false
     * @param database
     * @param input
     * @return
     * @throws Exception
     */
    public static boolean checkPassword(String database,String input){
        if(database == null || input == null){
            System.out.println("输入或查找为空");
            return false;
        }

        if(database.equalsIgnoreCase(input)){
            System.out.println("密码检查成功");
            return true;
        }else{
            System.out.println("密码检查失败");
            return false;
        }
    }
}
