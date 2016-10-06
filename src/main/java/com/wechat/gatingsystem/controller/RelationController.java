package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.Impl.DoorInfoServiceImpl;
import com.wechat.gatingsystem.service.Impl.RelationServiceImpl;
import com.wechat.gatingsystem.service.Impl.UserInfoServiceImpl;
import com.wechat.gatingsystem.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/indexRelation")
public class RelationController {

    @Autowired
    private RelationServiceImpl relationService;

    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;

    @Autowired
    private DoorInfoServiceImpl doorInfoServiceImpl;

    private HashMap<String, Integer> integerMap = new HashMap<String, Integer>();
    //用户管理的门，可以开的门
    @RequestMapping("/searchRelations")
    public void searchRelation(String userPhone, HttpServletRequest request, HttpServletResponse response) {

        List<Object> relationList = userInfoServiceImpl.selectRelaDoorByUserPhone(userPhone);
        JsonUtils mDoorJsonUtils = new JsonUtils();
        String json = JSON.toJSONString(relationList);
        mDoorJsonUtils.writeJson(json, request, response);

    }

    @RequestMapping("/getUserIdentity")
    public void getUserIdentity(Integer isAdmin) {
        integerMap.put("isAd", isAdmin);
    }

    @RequestMapping("/findUserId")
    public void findUserId(String userPhone) {

        Integer userId = userInfoServiceImpl.findUserId(userPhone);
        integerMap.put("userId", userId);
    }

    @RequestMapping("/findDoorId")
    public void findDoorId(String doorName) {

        Integer doorId = doorInfoServiceImpl.findDoorId(doorName);
        integerMap.put("userId", doorId);
    }

    @RequestMapping("/findUserMaxId")
    public void findUserMaxId() {

        Integer userId = userInfoServiceImpl.idMaxRecord();
        integerMap.put("doorId", userId+1);
    }

    @RequestMapping("/findDoorMaxId")
    public void findDoorMaxId() {

        Integer doorId = doorInfoServiceImpl.idMaxRecord();
        integerMap.put("doorId", doorId+1);
    }

    @RequestMapping("/insertRelation")
    public void insertRelation() {

        Integer isAd = integerMap.get("isAd");
        Integer doorId = integerMap.get("doorId");
        Integer userId = integerMap.get("userId");
        if (userId != null && doorId != null && isAd != null) {
            relationService.insertRelation(userId, doorId, isAd);
            integerMap.clear();
        }
    }

    @RequestMapping("/findDoorUser")
    public void findDoorUser(String doorName, HttpServletRequest request, HttpServletResponse response) {

        //这个类型不匹配的问题吗？
//        List<DoorInfo> door = doorInfoServiceImpl.finddoorByName(doorName);
//        dID = door.get(0).doorID;
        //java.util.HashMap can't cast to UserInfo
        Integer dID;
        Integer uID;
        Integer isAd;
        Integer doorId = doorInfoServiceImpl.findDoorId(doorName);
        dID = doorId;
        List<Relation> relationList = relationService.findUserIdInRelation(dID);
        List<Object> jsonList = new ArrayList<>();
        for (int i = 0; i < relationList.size(); i++) {

            Relation re = relationList.get(i);
            uID = re.userID;
            isAd = re.isAdmin;
            List<UserInfo> userList = userInfoServiceImpl.findUserInfoById(uID);

            UserInfo user = new UserInfo();
            if(userList != null) {
                user = userList.get(0);
            }

            if (isAd == 1) {
                user.setUserPhone(user.userPhone+"-1");
                user.userPhone = user.getUserPhone();
            } else {
                user.setUserPhone(user.userPhone+"-0");
                user.userPhone = user.getUserPhone();
            }
            jsonList.add(user);
        }
        String json = JSON.toJSONString(jsonList);
        JsonUtils.writeJson(json, request, response);
    }

    //删除relation里边的一条记录
    @RequestMapping("/deleteRelationByUDId")
    public void deleteRelationByUDId(String userPhone, String doorName) {

        Integer userId = userInfoServiceImpl.findUserId(userPhone);
        System.out.println(userId + "");
        Integer doorId = doorInfoServiceImpl.findDoorId(doorName);
        System.out.println(doorId + "");

        relationService.deleteRelationByDoorUserId(userId, doorId);
    }

}
