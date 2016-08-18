package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.dao.DoorInfoDAO;
import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.po.UserInfo;
import com.wechat.gatingsystem.service.IDoorInfoService;
import com.wechat.gatingsystem.service.IUserInfoService;
import com.wechat.gatingsystem.service.Impl.DoorInfoServiceImpl;
import com.wechat.gatingsystem.service.Impl.OpenRecordServiceImpl;
import com.wechat.gatingsystem.service.Impl.RelationServiceImpl;
import com.wechat.gatingsystem.service.Impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.spi.http.HttpContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.R;

@Controller
@RequestMapping(value = "/index")
public class DoorInfoController {

	@Autowired
	private DoorInfoServiceImpl doorInfoServiceImpl;

	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;

	@Autowired
	private RelationServiceImpl relationService;

	@Autowired
	private OpenRecordServiceImpl openRecordService;


	@RequestMapping(value = "/hello/{id}")
	@ResponseBody
	public Map hello(@PathVariable("id") int id){
		Map<String,Object> strMap = new HashMap<String,Object>();
		Relation relation = relationService.selectByRelationID(id);
		strMap.put("name",relation.getDoorID());
		return strMap;
	}

	@RequestMapping(value = "/doorTest/{name}")
	@ResponseBody
	public Map doorTest(@PathVariable("name") String name){
		Map<String,Object> strMap = new HashMap<String,Object>();
		doorInfoServiceImpl.deleteDoorByName(name);
		strMap.put("name",name);
		return null;
	}

}
