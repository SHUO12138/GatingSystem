package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.Impl.UserInfoServiceImpl;
import com.wechat.gatingsystem.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/indexUser")
public class UserController {

    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;

    @RequestMapping("/userRegister")
    public void userRegister(String uPhone, String uPassword, String uName, String uEmail,
                             HttpServletRequest request, HttpServletResponse response){
        UserInfo uInfo = new UserInfo();
        String json;
        String resp;
        //如果用户名存在，返回
        uInfo = userInfoServiceImpl.findByName(uName);
        if(uInfo != null){
            resp = "这个用户名已存在";
        }else {
            uInfo = new UserInfo();
            uInfo.userPhone = uPhone;
            uInfo.userPassword = uPassword;
            uInfo.userName = uName;
            uInfo.userEmail = uEmail;
            userInfoServiceImpl.insertUserInfo(uInfo);

            uInfo = userInfoServiceImpl.judgePassword(uName, uPassword);

            if (uInfo != null) {
                resp = "1";
            } else {
                resp = "0";
            }
        }
        json = JSON.toJSONString(resp);
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/judgePassword")
    public void judgePassword(String uName, String uPas,
                              HttpServletRequest request, HttpServletResponse response){

        UserInfo uInfo = userInfoServiceImpl.judgePassword(uName, uPas);

        Integer resp;
        if(uInfo != null){
            resp = 1;
        }else{
            resp = 0;
        }
        String json = JSON.toJSONString(resp);
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/updateUInfo")
    public void updateUInfo(String uName, String uPhone, String uEmail,
                            HttpServletRequest request, HttpServletResponse response){

        //更新
        UserInfo uInfo = new UserInfo();
        uInfo.userName = uName;
        uInfo.userPhone = uPhone;
        uInfo.userEmail = uEmail;
        userInfoServiceImpl.updateUserInfo(uInfo);

        //查询
        uInfo = new UserInfo();
        uInfo = userInfoServiceImpl.findByName(uName);
        String resp;
        if(uInfo.userPhone.equals(uPhone)){
            resp = "更新成功！";
        }else{
            resp = "更新失败";
        }
        String json = JSON.toJSONString(resp);
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/showUserInfo")
    public void showUserInfo(String uName,
                             HttpServletRequest request, HttpServletResponse response){

        List<UserInfo> uInfoList = new ArrayList<UserInfo>();
        UserInfo uInfo = userInfoServiceImpl.findByName(uName);
        uInfoList.add(uInfo);
        String resp = "";
        String json;
        if(uInfo != null){
            json = JSON.toJSONString(uInfoList);
            //json = JSON.toJSONString(uInfo.toString());
        }else {
            resp = "error";
            json = JSON.toJSONString(resp);
        }
        JsonUtils.writeJson(json, request, response);
    }
}
