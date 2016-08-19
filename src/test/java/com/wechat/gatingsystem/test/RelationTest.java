package com.wechat.gatingsystem.test;

import com.wechat.gatingsystem.dao.RelationDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/19.
 */
public class RelationTest  extends BaseTest {
    @Autowired
    private RelationDAO relationDAO;

    @Test
    public void testSelectByRelationID(){
        List<HashMap<Object,Object>>  map= relationDAO.selectRecordByRelation(1);
        System.out.println(map);
    }

    @Test
    public void testDeleteByDoorName(){

//        List<HashMap<Object,Object>> map= relationDAO.deleteByDoorName("test1");
//        System.out.println(map);
    }



}
