package com.wechat.gatingsystem.controller;

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
    public void finfAlldoorInfo(HttpServletRequest request,HttpServletResponse response){
    	 String jsonStr = doorInfoServiceImpl.finfAlldoorInfo();
    	 JsonUtils.writeJson(jsonStr, request, response);
    }
    
    
    
    @RequestMapping(value = "/selectRelationById/{id}")
    @ResponseBody
    public Map selectRelation(@PathVariable("id") int id) {

        Map<String, Object> strMap = new HashMap<String, Object>();
        Relation relation = relationService.selectByRelationID(id);
        strMap.put("name", relation.getDoorID());
        return strMap;
    }

    //http://localhost:8080/gatingsystem/index/findAllUser
    @RequestMapping(value = "/findAllUser")
    @ResponseBody
    public List<HashMap<Object, Object>> findAllUser(/*HttpServletRequest request,HttpServletResponse response*/) {

        List<HashMap<Object, Object>> strMap = userInfoServiceImpl.findAllUser();
        //JsonUtils.writeJson(strMap, request, response);
        return strMap;
    }

    //http://localhost:8080/gatingsystem/index/searchRecord
    @RequestMapping(value = "/searchRecord/{doorId}")
    @ResponseBody
    public List<HashMap<Object, Object>> searchRecord(@PathVariable("doorId") Integer doorID) {
        System.out.println(doorID + "!!!!!!!!!!");
        List<HashMap<Object, Object>> listMap = relationService.selectRecordByRelation(doorID);
        return listMap;
    }

    @RequestMapping(value = "/deleteDoor/{doorName}")
    @ResponseBody
    public void deleteDoor(@PathVariable("doorName") String doorName) {

        doorInfoServiceImpl.deleteByDoorName(doorName);
    }

}
