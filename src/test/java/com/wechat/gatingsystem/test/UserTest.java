package com.wechat.gatingsystem.test;

import com.wechat.gatingsystem.dao.UserInfoDAO;
import com.wechat.gatingsystem.po.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/20.
 */
public class UserTest extends BaseTest {
    @Autowired
    private UserInfoDAO userInfoDAO;

    @Test
    public void selectRelaByUPhone(){

        //应该得到三条数据
        List<Object> map = userInfoDAO.selectRelaDoorByUserPhone("15280923281");

        for(int i = 0; i < map.size(); i++) {
            System.out.println(map.size());
            System.out.println(map.get(i));
        }
    }
}