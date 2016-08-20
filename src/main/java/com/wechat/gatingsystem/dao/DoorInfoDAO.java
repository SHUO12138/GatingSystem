package com.wechat.gatingsystem.dao;


import java.util.List;

import com.wechat.gatingsystem.po.DoorInfo;



public interface DoorInfoDAO {

	List<DoorInfo> findAll();


	DoorInfo finddoorByID(int doorID);

	void insertDoorInfo(DoorInfo doorInfo);

	//删除doorInfo。删除doorInfo的同时，要删除relation open_record里边的记录
	void deleteByDoorName(String doorName);



}
