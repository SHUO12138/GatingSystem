package com.wechat.gatingsystem.service.Impl;

import com.wechat.gatingsystem.dao.UserInfoDAO;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<Object> selectRelaDoorByUserPhone(String userPhone) {

        List<Object> userDoorRelaList = userInfoDao.selectRelaDoorByUserPhone(userPhone);
        return userDoorRelaList;
    }

    @Override
    public UserInfo findByName(String uName) {
        UserInfo uInfo = userInfoDao.findByName(uName);
        return uInfo;
    }

    @Override
    public void insertUserInfo(UserInfo uInfo) {
        userInfoDao.insertUserInfo(uInfo);
    }

    @Override
    public UserInfo judgePassword(String uName, String uPassword) {
        UserInfo uInfo = userInfoDao.judgePassword(uName, uPassword);
        return uInfo;
    }

    @Override
    public void updateUserInfo(UserInfo uInfo) {

        userInfoDao.updateUserInfo(uInfo);
    }

    @Override
    public UserInfo findById(int uId) {

        UserInfo uInfo = userInfoDao.findById(uId);
        return uInfo;
    }
}
