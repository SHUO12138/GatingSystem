package com.wechat.gatingsystem.utils;


import com.wechat.gatingsystem.po.UserInfo;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lr on 2016/4/13/013.
 *
 */
public class JsonUtils {

    public static void writeJson(String jsonData, HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/plain;charset=UTF-8");
        String callbackFunName = request.getParameter("callbackparam");//得到js函数名称
        try {
            response.getWriter().write(callbackFunName + "(" + jsonData + ")");
        } catch (IOException e) {
            System.out.println("write to client exception with" + jsonData);
        }
    }

    public static void writeJson(List<String> jsonData, HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/plain;charset=UTF-8");
        String callbackFunName = request.getParameter("callbackparam");//得到js函数名称
        try {
            String json="";
            for(int i = 0; i < jsonData.size(); i++){
                json = jsonData.get(i).toString();
            }
            response.getWriter().write(callbackFunName + "(" + json + ")");

        } catch (IOException e) {
            System.out.println("write to client exception with" + jsonData);
        }
    }
//
//    public static UserInfo insert(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("text/plain;charset=UTF-8");
//        UserInfo userInfo = new UserInfo();
//        //得到js函数名称
//        String callbackFunName = request.getParameter("insertPhone");
//
//        String uPhone = request.getParameter("phone");
//        userInfo.setUserPhone(uPhone);
//        userInfo.userPhone = userInfo.getUserPhone();
//
//        return userInfo;
//    }

//    public static UserInfo updateUser(HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("text/plain;charset=UTF-8");
//        UserInfo userInfo = new UserInfo();
//        //得到js函数名称
//        String callbackFunName = request.getParameter("updateUser");
//
//        String uPhone = request.getParameter("userPhone");
//        String uName = request.getParameter("userName");
//        String uInfo = request.getParameter("userInfo");
//
//        userInfo.setUserName(uName);
//        userInfo.setUserMoreInfo(uInfo);
//        userInfo.setUserPhone(uPhone);
//        userInfo.userPhone = userInfo.getUserPhone();
//        userInfo.userName = userInfo.getUserName();
//        userInfo.userMoreInfo = userInfo.getUserMoreInfo();
//        return userInfo;
//    }

}
