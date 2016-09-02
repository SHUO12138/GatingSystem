package com.wechat.gatingsystem.test;

import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.service.Impl.RelationServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wechat.gatingsystem.dao.DoorInfoDAO;

import java.util.HashMap;
import java.util.Map;

public class DoorInfoDaoTest extends BaseTest{
	@Autowired
	private DoorInfoDAO doorInfoDao;



	@Test
	public void  test(){
		doorInfoDao.findAll();
		System.out.println("@@@@@@@@@"+ doorInfoDao.findAll().size());
	}

	@Test
	public void testHello(){

	}

	@Test
	public void testDeleteByNme(){
		doorInfoDao.deleteByDoorName("test");
	}


}
