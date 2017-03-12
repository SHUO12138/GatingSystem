package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.dao.DoorInfoDAO;
import com.wechat.gatingsystem.dao.OpenRecordDAO;
import com.wechat.gatingsystem.dao.RelationDAO;
import com.wechat.gatingsystem.dao.UserInfoDAO;
import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.Impl.RelationServiceImpl;
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
    private RelationDAO relationDAO;
    @Autowired
    private UserInfoDAO userInfoDAO;
    @Autowired
    private DoorInfoDAO doorInfoDAO;
    @Autowired
    private OpenRecordDAO openRecordDAO;

    @RequestMapping("/insertIntoRelation")
    public void insertIntoRelation(String uName, String dName, int isAd,
                               HttpServletRequest request, HttpServletResponse response){

        String resp;
        String json;
        DoorInfo dInfo = doorInfoDAO.findByName(dName);
        UserInfo uInfo = userInfoDAO.findByName(uName);
        if(dInfo != null && uInfo != null){
            Relation re = new Relation();
            re.userID = uInfo.userID;
            re.doorID = dInfo.doorID;
            re.isAdmin = isAd;
            relationService.insertRelation(re);
            re = new Relation();
            re = relationService.findReByUDid(uInfo.userID, dInfo.doorID);
            if(re != null){
                resp = "1";
            }else{
                resp = "对不起添加用户失败";
            }
        }else{
            resp = "对不起，不能添加用户";
        }
        json = JSON.toJSONString(resp);
        JsonUtils.writeJson(json, request, response);
    }

    @RequestMapping("/lookUserOfDoor")
    public void lookUserOfDoor(String dName, String uName,
                               HttpServletRequest request, HttpServletResponse response){

        String json;
        DoorInfo dInfo = doorInfoDAO.findByName(dName);
        UserInfo userSelf = userInfoDAO.findByName(uName);
        if(dInfo != null){
            List<Relation> reList = relationService.selectByDid(dInfo.doorID);
            List<UserInfo> uList = new ArrayList<UserInfo>();
            for(Relation re: reList){
                UserInfo uInfo = userInfoDAO.findById(re.userID);
                if(uInfo.userID != userSelf.userID) {
                    if(re.isAdmin == 1){
                        uInfo.userPhone += "-1";
                    }
                    else if(re.isAdmin == 0){
                        uInfo.userPhone += "-0";
                    }
                    uList.add(uInfo);
                }
            }
            if(!uList.isEmpty()){
                json = JSON.toJSONString(uList);
            }else{
                json = JSON.toJSONString("");
            }
        }else{
            json = JSON.toJSONString("");
        }
        JsonUtils.writeJson(json,request, response);
    }

    @RequestMapping("/deleteRelation")
    public void deleteRelation(String dName, String uName,
                           HttpServletRequest request, HttpServletResponse response){

        String resp;
        String json;
        DoorInfo dInfo = doorInfoDAO.findByName(dName);
        UserInfo uInfo = userInfoDAO.findByName(uName);
        if(dInfo != null && uInfo != null){
            Relation re = relationService.findReByUDid(uInfo.userID, dInfo.doorID);
            relationService.deleteReByUDid(uInfo.userID, dInfo.doorID);
            openRecordDAO.deleteRecord(re.relationID);

            resp = "1";
        }else{
            resp = "0";
        }

        json = JSON.toJSONString(resp);
        JsonUtils.writeJson(json, request, response);
    }

}
