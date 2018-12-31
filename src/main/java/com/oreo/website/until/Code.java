package com.oreo.website.until;

import java.util.Date;

public class Code {
    int cId;
    String cMail;
    String cCode;
    Date cLastTime;

    public Date getuLastTime() {
        return cLastTime;
    }

    public void setuLastTime(Date uLastTime) {
        this.cLastTime = uLastTime;
    }

    public int getcId() {
        return cId;
    }

    public String getcCode() {
        return cCode;
    }

    public String getcMail() {
        return cMail;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public void setcMail(String cMail) {
        this.cMail = cMail;
    }
}
