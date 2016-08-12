package com.wechat.gatingsystem.dao;

import java.util.List;

import com.wechat.gatingsystem.po.DoorInfo;



public interface DoorInfoDAO {

	List<DoorInfo> findAll();

	DoorInfo finddoorByID(int doorID);

	void insertDoorInfo(DoorInfo doorInfo);

	void deleteDoorByName(String name);

	/*void updateDoorInfoByID(DoorInfo doorInfo);*/

}
