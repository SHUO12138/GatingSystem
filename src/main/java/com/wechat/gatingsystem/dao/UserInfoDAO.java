package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/10.
 */
public interface UserInfoDAO {

    List<UserInfo> findAllUser();

    UserInfo findUserInfoByID(int userID);

    void insertUserInfo(UserInfo userInfo);

    void deleteUserInfoByPhone(String userPhone);

    //查找与某一个user相关联的门的信息
    List<Object> selectRelaDoorByUserPhone(String userPhone);

    //更新用户信息
    void updateUser(UserInfo userInfo);

    //通过电话号码查找用户
    List<UserInfo> findUserInfoByPhone(String phone);
}
