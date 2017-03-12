package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/10.
 */
public interface IUserInfoService {


    List<Object> selectRelaDoorByUserPhone(String userPhone);

    UserInfo findByName(String uName);

    void insertUserInfo(UserInfo uInfo);

    UserInfo judgePassword(String uName, String uPassword);

    void updateUserInfo(UserInfo uInfo);

    UserInfo findById(int uId);
}
