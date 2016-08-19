package com.wechat.gatingsystem.dao;


import java.util.HashMap;
import java.util.List;

import com.wechat.gatingsystem.po.DoorInfo;



public interface DoorInfoDAO {

	List<DoorInfo> findAll();


	DoorInfo finddoorByID(int doorID);

	void insertDoorInfo(DoorInfo doorInfo);

	void deleteByDoorName(String doorName);



}
