package com.wechat.gatingsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.dao.DoorInfoDAO;
import com.wechat.gatingsystem.po.DoorInfo;
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

	@RequestMapping(value = "/hello")
	@ResponseBody
	public Map hello(){

		relationService.selectByRelationID(1);

		Map<String,String> strMap = new HashMap<String,String>();
		strMap.put("name","name");
		return strMap;
	}


	@RequestMapping("/input/{name}")
	@ResponseBody
	public Map input(@PathVariable("name") String name){

		Map<String,String> strMap = new HashMap<String,String>();
		strMap.put("name",name);
		return strMap;

	}

	@RequestMapping("/html")
	public String redirect(){

		System.out.println("test!!!");
		return "hello";
	}

}
