package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/10.
 */
public interface UserInfoDAO {

    //查找与某一个user相关联的门的信息
    List<Object> selectRelaDoorByUserPhone(String userPhone);

    //查找这个用户是否已经注册过
    UserInfo findByName(String uName);

    //插入用户信息
    void insertUserInfo(UserInfo uInfo);

    //判断用户输入的账号密码是否匹配
    UserInfo judgePassword(String uName, String uPassword);

    //更新用户信息
    void updateUserInfo(UserInfo uInfo);

    //通过id查找用户
    UserInfo findById(int uId);
}
