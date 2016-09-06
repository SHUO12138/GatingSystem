package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.sun.javafx.beans.annotations.NonNull;
import com.sun.xml.internal.ws.encoding.XMLHTTPBindingCodec;
import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.Impl.DoorInfoServiceImpl;
import com.wechat.gatingsystem.service.Impl.OpenRecordServiceImpl;
import com.wechat.gatingsystem.service.Impl.RelationServiceImpl;
import com.wechat.gatingsystem.service.Impl.UserInfoServiceImpl;
import com.wechat.gatingsystem.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/index")
public class DoorController {

    @Autowired
    private DoorInfoServiceImpl doorInfoServiceImpl;

    @Autowired
    private UserInfoServiceImpl userInfoServiceImpl;

    @Autowired
    private RelationServiceImpl relationService;

    @Autowired
    private OpenRecordServiceImpl openRecordService;

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
    public void findUserByPhone(HttpServletRequest request, HttpServletResponse response, String userPhone){

        System.out.println(userPhone);
        JsonUtils userJsonUtils = new JsonUtils();
        List<UserInfo> userInfo = userInfoServiceImpl.findUserInfoByPhone(userPhone);
        String json = JSON.toJSONString(userInfo);
        System.out.println(json.length());
        userJsonUtils.writeJson(json, request, response);
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

    //用户管理的门，可以开的门
    @RequestMapping("/searchRelations")
    public void searchRelation(String userPhone, HttpServletRequest request, HttpServletResponse response) {

        List<Object> relationList = userInfoServiceImpl.selectRelaDoorByUserPhone(userPhone);
        JsonUtils mDoorJsonUtils = new JsonUtils();
        String json = JSON.toJSONString(relationList);
        System.out.println(json);
        mDoorJsonUtils.writeJson(json, request, response);

    }


    //查找所有门的信息
    @RequestMapping("/findAllDoors")
    public void findAllDoors(HttpServletRequest request, HttpServletResponse response) {

        List<DoorInfo> doorInfoList = doorInfoServiceImpl.findAll();
        //transform List into String in order to output
        String json = JSON.toJSONString(doorInfoList);
        JsonUtils.writeJson(json, request, response);
    }

    //增加门的信息
    @RequestMapping("/insertDoor")
    public void insertDoor(String doorName, String doorLocation, String doorInfo,
                           HttpServletRequest request, HttpServletResponse response) {
        Date doorCreateTime = new Date();

        DoorInfo door = new DoorInfo();
        door.setDoorName(doorName);
        door.doorName = door.getDoorName();
        door.setDoorCreateTime(doorCreateTime);
        door.doorCreateTime = door.getDoorCreateTime();
        door.setDoorMoreInfo(doorInfo);
        door.doorMoreInfo = door.getDoorMoreInfo();
        door.setDoorLocation(doorLocation);
        door.doorLocation = door.getDoorLocation();

        doorInfoServiceImpl.insertDoorInfo(door);
    }

    //通过门的名字查找门的信息
    @RequestMapping("/findDoorInfoByMame")
    public void findDoorByName(String doorName, HttpServletRequest request, HttpServletResponse response){

        List<DoorInfo> door = doorInfoServiceImpl.finddoorByName(doorName);
        String json = JSON.toJSONString(door);
        JsonUtils.writeJson(json, request, response);
    }

    //删除某一扇门，以及与他相关联的信息，但是只有relation，record里边读有它的信息的时候才可以删掉
    @RequestMapping("/deleteDoorAndRelations")
    public void deleteByDoorName(String doorName){

        doorInfoServiceImpl.deleteByDoorName(doorName);
    }

    @RequestMapping("/insertRelation")
    public void insertRelation(int userId, int doorId, int isAdmin){

        relationService.insertRelation(userId, doorId, isAdmin);
    }


    @RequestMapping(value = "/selectRelationById/{id}")
    @ResponseBody
    public Map selectRelation(@PathVariable("id") int id,
                              HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> strMap = new HashMap<String, Object>();
        Relation relation = relationService.selectByRelationID(id);

        String json = JSON.toJSONString(relation);
        JsonUtils.writeJson(json, request, response);
        strMap.put("name", relation.getDoorID());
        return strMap;
    }

    //http://localhost:8080/gatingsystem/index/searchRecord
    @RequestMapping(value = "/searchRecord/{doorId}")
    @ResponseBody
    public List<HashMap<Object, Object>> searchRecord(@PathVariable("doorId") Integer doorID) {

        List<HashMap<Object, Object>> listMap = relationService.selectRecordByRelation(doorID);
        return listMap;
    }

    @RequestMapping(value = "/deleteDoor/{doorName}")
    @ResponseBody
    public void deleteDoor(@PathVariable("doorName") String doorName) {

        doorInfoServiceImpl.deleteByDoorName(doorName);
    }

}
