package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.DoorInfo;

public interface DoorInfoDAO {

	//添加门的信息
	void insertDoorInfo(DoorInfo dInfo);

	//根据门的名字查找门
	DoorInfo findByName(String dName);

	//删除一扇门的信息
	void deleteDoor(String dName);

	//通过id查询门
	DoorInfo selectDoorById(int dId);
}
