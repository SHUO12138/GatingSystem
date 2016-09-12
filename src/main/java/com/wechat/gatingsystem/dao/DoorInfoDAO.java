package com.wechat.gatingsystem.dao;


import java.util.List;

import com.wechat.gatingsystem.po.DoorInfo;



public interface DoorInfoDAO {

	List<DoorInfo> findAll();


	DoorInfo finddoorByID(int doorID);

	//增加一个门的信息
	void insertDoorInfo(DoorInfo doorInfo);

	//删除doorInfo。删除doorInfo的同时，要删除relation open_record里边的记录。
	//但是这个需要relation，open_record里边同时有关于这个门的记录的时候才可以执行成功
	void deleteByDoorName(String doorName);

	//根据door名字选出doot
	List<DoorInfo> finddoorByName(String name);

	//根据名字查找出door_id
	int findDoorId(String doorName);

}
