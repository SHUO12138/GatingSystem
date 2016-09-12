package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.Impl.DoorInfoServiceImpl;
import com.wechat.gatingsystem.service.Impl.RelationServiceImpl;
import com.wechat.gatingsystem.service.Impl.UserInfoServiceImpl;
import com.wechat.gatingsystem.utils.JsonUtils;

import net.sf.json.JSONArray;
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

    private Integer uID;
    private Integer isAd;
    private Integer dID;

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

        isAd = isAdmin;
    }

    @RequestMapping("/findUserId")
    public void findUserId(String userPhone, HttpServletRequest request, HttpServletResponse response) {

        Integer userId = userInfoServiceImpl.findUserId(userPhone);
        uID = userId;
        String json = JSON.toJSONString(userId);
        JsonUtils.writeJson(json, request, response);
    }

    //通过门的名字查找id
    @RequestMapping("/findDoorId")
    public void findDoorId(String doorName, HttpServletRequest request, HttpServletResponse response) {

        int doorId = doorInfoServiceImpl.findDoorId(doorName);
        dID = doorId;
        String json = JSON.toJSONString(doorId);
        JsonUtils.writeJson(json, request, response);

    }

    @RequestMapping("/insertRelation")
    public void insertRelation() {

        if (uID != null && dID != null && isAd != null) {
            System.out.println(uID + " " + dID + " " + isAd);
            relationService.insertRelation(uID, dID, isAd);
        }
    }

    @RequestMapping("/findDoorUser")
    public void findDoorUser(String doorName, HttpServletRequest request, HttpServletResponse response) {

        //这个类型不匹配的问题吗？
//        List<DoorInfo> door = doorInfoServiceImpl.finddoorByName(doorName);
//        dID = door.get(0).doorID;
        //java.util.HashMap can't cast to UserInfo
        int doorId = doorInfoServiceImpl.findDoorId(doorName);
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
    public void deleteRelationByUId(String userPhone, String doorName) {

        Integer userId = userInfoServiceImpl.findUserId(userPhone);
        uID = userId;
        int doorId = doorInfoServiceImpl.findDoorId(doorName);
        dID = doorId;

        relationService.deleteRelationByDoorUserId(uID, dID);
    }

}
