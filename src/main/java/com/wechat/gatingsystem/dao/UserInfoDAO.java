package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/10.
 */
public interface UserInfoDAO {

    List<UserInfo> findAllUser();

    //根据名字查找出user_id
    Integer findUserId(String userPhone);

    void insertUserInfo(UserInfo userInfo);

    void deleteUserInfoByPhone(String userPhone);

    //查找与某一个user相关联的门的信息
    List<Object> selectRelaDoorByUserPhone(String userPhone);

    //更新用户信息
    void updateUser(UserInfo userInfo);

    //通过电话号码查找用户是否存在
    List<UserInfo> findUserInfoByPhone(String phone);

    //显示存在的用户
    List<UserInfo> showUserByPhone(String phone);

    //通过用户id查找用户
    List<UserInfo> findUserInfoById(Integer userId);

    //查找最後一個數據的id
    Integer idMaxRecord();
}
