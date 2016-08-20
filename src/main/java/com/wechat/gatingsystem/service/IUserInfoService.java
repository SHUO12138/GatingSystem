package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/10.
 */
public interface IUserInfoService {

    List<HashMap<Object, Object>> findAllUser();

    UserInfo findUserInfoByID(int userID);

    void insertUserInfo(UserInfo userInfo);

    void deleteUserInfoByPhone(String userPhone);

    List<HashMap<Object, Object>> selectRelaDoorByUserPhone(String userPhone);
}
