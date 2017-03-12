package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.dao.DoorInfoDAO;
import com.wechat.gatingsystem.dao.OpenRecordDAO;
import com.wechat.gatingsystem.dao.RelationDAO;
import com.wechat.gatingsystem.dao.UserInfoDAO;
import com.wechat.gatingsystem.po.DoorInfo;
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
@RequestMapping(value = "/indexDoor")
public class DoorController {

    @Autowired
    private DoorInfoServiceImpl doorInfoServiceImpl;

    @Autowired
    private RelationDAO relationDAO;
    @Autowired
    private UserInfoDAO userInfoDAO;
    @Autowired
    private DoorInfoDAO doorInfoDAO;
    @Autowired
    private OpenRecordDAO openRecordDAO;

    @RequestMapping("/insertDoorInfo")
    public void insertDoorInfo(String uName, String dName, String dNum, String dInfo,
                               HttpServletRequest request, HttpServletResponse response) {

        String resp;
        DoorInfo doorInfo = new DoorInfo();
        DoorInfo doorInsert = new DoorInfo();
        doorInfo = doorInfoServiceImpl.findByName(dName);
        if (doorInfo != null) {
            resp = "这个门已经存在!";
        } else {
            doorInsert.doorName = dName;
            doorInsert.doorLocation = dNum;
            doorInsert.doorMoreInfo = dInfo;

            Date date = new Date();
//        DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm");
//        String time = format.format(date);
            doorInsert.doorCreateTime = date;

            doorInfoServiceImpl.insertDoorInfo(doorInsert);
            doorInfo = doorInfoServiceImpl.findByName(dName);

            if (doorInfo != null) {
                UserInfo userInfo = userInfoDAO.findByName(uName);

                Relation relation = new Relation();
                relation = relationDAO.findReByUDid(userInfo.userID, doorInfo.doorID);
                if (relation != null) {
                    resp = "关系记录中这扇门已存在，请核对";
                } else {
                    relation = new Relation();
                    relation.userID = userInfo.userID;
                    relation.doorID = doorInfo.doorID;
                    relation.isAdmin = 1;
                    relationDAO.insertRelation(relation);
                    //relation = new Relation();
                    relation = relationDAO.findReByUDid(userInfo.userID, doorInfo.doorID);
                    if (relation != null) {
                        resp = "1";
                    } else {
                        resp = "添加信息失败";
                    }
                }
            } else {
                resp = "添加信息失败";
            }
        }
        String json = JSON.toJSONString(resp);
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/selectDoorById")
    public void selectDoorById(String uName,
                               HttpServletRequest request, HttpServletResponse response) {

        String json;
        UserInfo uInfo = userInfoDAO.findByName(uName);
        List<Relation> reList = relationDAO.selectByUid(uInfo.userID);
        List<DoorInfo> dList = new ArrayList<DoorInfo>();
        //success
        for (Relation re : reList) {
            DoorInfo dInfo = new DoorInfo();
            dInfo = doorInfoDAO.selectDoorById(re.doorID);
            if(re.isAdmin == 1) {
                dInfo.doorLocation += "-1";
            }else{
                dInfo.doorLocation += "-0";
            }
            dList.add(dInfo);
            System.out.println(re.doorID);
        }
        if (!dList.isEmpty()) {
            json = JSON.toJSONString(dList);
        }else{
            json = JSON.toJSONString("");
        }
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/getManagedDoor")
    public void getManagedDoor(String uName,
                               HttpServletRequest request, HttpServletResponse response){
        String json;
        UserInfo uInfo = userInfoDAO.findByName(uName);
        List<Relation> reList = relationDAO.selectByUid(uInfo.userID);
        List<DoorInfo> dList = new ArrayList<DoorInfo>();
        //success
        for (Relation re : reList) {
            DoorInfo dInfo = new DoorInfo();
            dInfo = doorInfoDAO.selectDoorById(re.doorID);
            if(re.isAdmin == 1) {
                dList.add(dInfo);
                System.out.println(re.doorID);
            }
        }
        if (!dList.isEmpty()) {
            json = JSON.toJSONString(dList);
        }else{
            json = JSON.toJSONString("");
        }
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/deleteDoor")
    public void deleteDoor(String dName, String uName,
                           HttpServletRequest request, HttpServletResponse response){

        String resp;
        String json;
        DoorInfo dInfo = doorInfoServiceImpl.findByName(dName);
        doorInfoServiceImpl.deleteDoor(dName);
        if(dInfo != null){
            List<Relation> reList = relationDAO.selectByDid(dInfo.doorID);
            relationDAO.deleteRelation(dInfo.doorID);
            for(Relation re: reList){
                openRecordDAO.deleteRecord(re.relationID);
            }
//            getManagedDoor(uName, request, response);
            resp = "1";
        }else{
            resp = "0";
        }

        json = JSON.toJSONString(resp);
        JsonUtils.writeJson(json, request, response);
    }
}
