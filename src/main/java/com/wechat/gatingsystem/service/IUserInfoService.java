package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.UserInfo;

import java.util.List;

/**
 * Created by never on 2016/8/10.
 */
public interface IUserInfoService {

    List<UserInfo> findAllUser();

    UserInfo findUserInfoByID(int userID);

    void insertUserInfo(UserInfo userInfo);

    void deleteUserInfoByPhone(String userPhone);
}
