package com.wechat.gatingsystem.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wechat.gatingsystem.dao.DoorInfoDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ContextHierarchy({
	@ContextConfiguration(name = "parnet",locations = {
		"classpath*:/applicationContext-common.xml",
		"classpath*:/applicationContext-mybatis.xml"
	}),
	@ContextConfiguration(name = "child",locations = {
		"classpath*:/applicationContext-mvc.xml"
	})
})
public class BaseTest {
	@Autowired
	private DoorInfoDAO doorInfoDao;
//	@Test
//	public void  test(){
//		try {
//			doorInfoDao.findAll();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("@@@@@@@@@"+ doorInfoDao.findAll().size());
//	}
}
