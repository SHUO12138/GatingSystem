package com.wechat.gatingsystem.service.Impl;

import com.wechat.gatingsystem.dao.UserInfoDAO;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public List<UserInfo> findAllUser() {

        List<UserInfo> userList = new ArrayList<UserInfo>();

        userList = userInfoDao.findAllUser();
        for(int i = 0; i < userList.size(); i++){
            System.out.println(userList.get(i).userName + "");
        }
        return userList;
    }

    @Override
    public UserInfo findUserInfoByID(int userID) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserID(1);
        userInfo.userID = userInfo.getUserID();

        userInfo = userInfoDao.findUserInfoByID(userID);
        return userInfo;
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {

        List<UserInfo> userList = new ArrayList<UserInfo>();

        //look whether the user exists
        boolean flg = false;
        userList = userInfoDao.findAllUser();
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).userPhone.equals(userInfo.userPhone)){
                flg = true;
                break;
            }
        }
        if(flg == false) {
            userInfoDao.insertUserInfo(userInfo);
            System.out.println("Insert successfully!!");
        }
        else{
            System.out.println("This user already exists!");
        }
    }

    @Override
    public void deleteUserInfoByPhone(String userPhone) {

        List<UserInfo> userList = new ArrayList<UserInfo>();

        UserInfo userInfo = new UserInfo();
        userInfo.setUserPhone(userPhone);
        userInfo.userPhone = userInfo.getUserPhone();

        //look whether the user exists
        //if flg == true.can we delete user
        boolean flg = false;
        userList.clear();
        userList = userInfoDao.findAllUser();
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).userPhone.equals(userInfo.userPhone)){
                flg = true;
                break;
            }
        }
        if(flg) {
            userInfoDao.deleteUserInfoByPhone(userPhone);
            System.out.println("delete successfully!!");
        }
        else{
            System.out.println("This user don't exist!");
        }

    }


}
