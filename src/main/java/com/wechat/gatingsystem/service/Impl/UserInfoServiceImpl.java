package com.wechat.gatingsystem.service.Impl;

import com.wechat.gatingsystem.dao.UserInfoDAO;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/10.
 *
 */

@Repository("userInfoServiceImpl")
public class UserInfoServiceImpl implements IUserInfoService{


    @Autowired
    private UserInfoDAO userInfoDao;

    @Override
    public List<HashMap<Object, Object>> findAllUser() {

        List<HashMap<Object, Object>> userList = userInfoDao.findAllUser();
        return userList;
    }

    @Override
    public UserInfo findUserInfoByID(int userID) {

        UserInfo userInfo;
        userInfo = userInfoDao.findUserInfoByID(userID);
        return userInfo;
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {

        userInfoDao.insertUserInfo(userInfo);

    }

    @Override
    public void deleteUserInfoByPhone(String userPhone) {

        userInfoDao.deleteUserInfoByPhone(userPhone);
    }

    @Override
    public List<HashMap<Object, Object>> selectRelaDoorByUserPhone(String userPhone) {

        List<HashMap<Object, Object>> userDoorRelaList = userInfoDao.selectRelaDoorByUserPhone(userPhone);
        return userDoorRelaList;
    }


}
