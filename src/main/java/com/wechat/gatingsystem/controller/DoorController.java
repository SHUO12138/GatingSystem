package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
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

    @RequestMapping("/finfAlldoorInfo")
    public void finfAlldoorInfo(HttpServletRequest request, HttpServletResponse response) {

        List<DoorInfo> doorInfoList = doorInfoServiceImpl.findAll();
        //transform List into String in order to output
        String json = JSON.toJSONString(doorInfoList);
        JsonUtils.writeJson(json, request, response);
    }

    //http://localhost:8080/gatingsystem/index/findAllUser

    /**
     * 前段和服务器交互的时候，出现了500错误
     * 修改：
     * 1.去掉了@ResponseBody
     * 2.去掉了@RequestMapping里边的value=
     * 3.把userInfoServiceImpl.findAllUser()的返回值改为List<UserInfo>
     * 4.接口没有map返回值
     */
    @RequestMapping("/findAllUser")
    public void findAllUser(HttpServletRequest request, HttpServletResponse response) {

        List<UserInfo> strMap = userInfoServiceImpl.findAllUser();
        String json = JSON.toJSONString(strMap);
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/findUserByPhone")
    public void findUserByPhone(HttpServletRequest request, HttpServletResponse response){
        JsonUtils userJsonUtils = new JsonUtils();
        String uPhone =userJsonUtils.getUserPhone(request, response);
        UserInfo userInfo = userInfoServiceImpl.findUserInfoByPhone(uPhone);
        String json = JSON.toJSONString(userInfo);
        System.out.println(json);
        userJsonUtils.writeSingleUserJson(json, request, response);
    }

    @RequestMapping("/insertUser")
    public void insertUser(HttpServletRequest request, HttpServletResponse response) {

        UserInfo userInfo = new UserInfo();
        JsonUtils jsonUtils = new JsonUtils();
        userInfo = jsonUtils.insert(request, response);
        userInfoServiceImpl.insertUserInfo(userInfo);

    }

    //http://localhost:8080/gatingsystem/index/updateUser
    @RequestMapping("/updateUser")
    public void updateUser(HttpServletRequest request, HttpServletResponse response) {

        UserInfo userInfo = new UserInfo();
        JsonUtils jsonUtils = new JsonUtils();
        userInfo = jsonUtils.updateUser(request, response);
        userInfoServiceImpl.updateUser(userInfo);
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
