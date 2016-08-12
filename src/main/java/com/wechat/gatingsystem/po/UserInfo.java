package com.wechat.gatingsystem.po;

/**
 * Created by never on 2016/8/10.
 */
public class UserInfo {

    public int userID;
    public String userName;
    public String userPhone;
    public String userMoreInfo;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserMoreInfo() {
        return userMoreInfo;
    }

    public void setUserMoreInfo(String userMoreInfo) {
        this.userMoreInfo = userMoreInfo;
    }
}
