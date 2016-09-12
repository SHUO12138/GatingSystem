package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.po.Relation;
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
    private RelationServiceImpl relationService;



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
        //json == null json: []
        String json = JSON.toJSONString(door);
        //门不存在
        if(json.length() <= 2){
            json = "0";
        }
        //门已经存在
        else{
            json = "1";
        }
        JsonUtils.writeJson(json, request, response);
    }

    //删除某一扇门，以及与他相关联的信息，但是只有relation，record里边读有它的信息的时候才可以删掉
    @RequestMapping("/deleteDoorAndRelations")
    public void deleteByDoorName(String doorName){

        doorInfoServiceImpl.deleteByDoorName(doorName);
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
