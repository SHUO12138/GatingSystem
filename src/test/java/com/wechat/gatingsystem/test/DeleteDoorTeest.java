package com.wechat.gatingsystem.test;

import com.wechat.gatingsystem.dao.DoorInfoDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/19.
 *
 */
public class DeleteDoorTeest {

    @Autowired
    private DoorInfoDAO doorInfoDAO;

    @Test
    public void testDeleteByDoorName(){

       /* List<HashMap<Object,Object>> map= doorInfoDAO.deleteByDoorName("test1");
        System.out.println(map);*/
    }
}
