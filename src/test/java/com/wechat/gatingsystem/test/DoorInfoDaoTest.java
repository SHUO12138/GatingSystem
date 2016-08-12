package com.wechat.gatingsystem.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wechat.gatingsystem.dao.DoorInfoDAO;

public class DoorInfoDaoTest extends BaseTest{
	@Autowired
	private DoorInfoDAO doorInfoDao;
	@Test
	public void  test(){
		doorInfoDao.findAll();
		System.out.println("@@@@@@@@@"+ doorInfoDao.findAll().size());
	}


}
