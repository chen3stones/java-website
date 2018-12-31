package com.oreo.website.until;

import org.springframework.stereotype.Component;
import java.lang.String;
@Component
public class User {
    private int uID;
    private String uName;
    private String uPassword;
    private String uMail;
    private int authority;

    /*
    public User(String name,String password,String mail,int authority){
        this.uName = name;
        this.uMail = mail;
        this.uPassword = password;
        this.authority = authority;
    }
    */

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public void setuMail(String uMail) {
        this.uMail = uMail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public String getuMail() {
        return uMail;
    }

    public int getuID() {
        return uID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "name:"+uName+",mail:"+uMail;
    }
}
