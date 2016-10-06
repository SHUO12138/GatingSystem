package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/10.
 */
public interface IUserInfoService {

    List<UserInfo> findAllUser();

    Integer findUserId(String userPhone);

    void insertUserInfo(UserInfo userInfo);

    void deleteUserInfoByPhone(String userPhone);

    List<Object> selectRelaDoorByUserPhone(String userPhone);

    void updateUser(UserInfo userInfo);

    List<UserInfo> findUserInfoByPhone(String phone);

    List<UserInfo> showUserByPhone(String phone);

    List<UserInfo> findUserInfoById(Integer userId);

    Integer idMaxRecord();
}
