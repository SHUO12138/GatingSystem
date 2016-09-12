package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.Impl.UserInfoServiceImpl;
import com.wechat.gatingsystem.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/indexUser")
public class UserController {

    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;

    //http://localhost:8080/gatingsystem/index/findAllUser
    /**
     * 前段和服务器交互的时候，出现了500错误
     * 修改：
     * 1.去掉了@ResponseBody
     * 2.去掉了@RequestMapping里边的value=
     * 3.把userInfoServiceImpl.findAllUser()的返回值改为List<UserInfo>
     */
    //查找所有用户的信息
    @RequestMapping("/findAllUser")
    public void findAllUser(HttpServletRequest request, HttpServletResponse response) {

        List<UserInfo> strMap = userInfoServiceImpl.findAllUser();
        String json = JSON.toJSONString(strMap);
        JsonUtils.writeJson(json, request, response);
    }

    //通过用户手机查找用户
    @RequestMapping("/findUserByPhone")
    public void findUserByPhone(String userPhone, HttpServletRequest request, HttpServletResponse response){

        List<UserInfo> user = userInfoServiceImpl.findUserInfoByPhone(userPhone);
        //json == null json: []
        String json = JSON.toJSONString(user);
        //用户不存在
        if(json.length() <= 2){
            json = "0";
        }
        //用户已经存在
        else{
            json = "1";
        }
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/showUserByPhone")
    public void showUserByPhone(String userPhone, HttpServletRequest request, HttpServletResponse response){

        List<UserInfo> user = userInfoServiceImpl.showUserByPhone(userPhone);
        String json = JSON.toJSONString(user);
        JsonUtils.writeJson(json, request, response);
    }

    //增加用户，这个时候只是增加了用户的手机号
    @RequestMapping("/insertUser")
    public void insertUser(String userPhone) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserPhone(userPhone);
        userInfo.userPhone = userInfo.getUserPhone();

        userInfoServiceImpl.insertUserInfo(userInfo);

    }

    //更新用户，增加手机号之外的信息
    //http://localhost:8080/gatingsystem/index/updateUser
    @RequestMapping("/updateUser")
    public void updateUser(String userPhone, String userName, String userInfo) {

        UserInfo user = new UserInfo();
        user.setUserName(userName);
        user.setUserMoreInfo(userInfo);
        user.setUserPhone(userPhone);
        user.userPhone = user.getUserPhone();
        user.userName = user.getUserName();
        user.userMoreInfo = user.getUserMoreInfo();

        userInfoServiceImpl.updateUser(user);

    }

}
