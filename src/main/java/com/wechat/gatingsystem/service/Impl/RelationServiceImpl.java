package com.wechat.gatingsystem.service.Impl;

import com.wechat.gatingsystem.dao.RelationDAO;
import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/11.
 *
 */

@Repository("relationServiceImpl")
public class RelationServiceImpl implements IRelationService{

    @Autowired
    private RelationDAO relationDao;

    @Override
    public Relation selectByRelationID(int relationID) {

        Relation relation = relationDao.selectByRelationID(relationID);
        return relation;
    }

    @Override
    public List<HashMap<Object, Object>> selectRecordByRelation(Integer doorId) {

        List<HashMap<Object, Object>> listMap = relationDao.selectRecordByRelation(doorId);
        return listMap;
    }

    @Override
    public void insertRelation(int userID, int doorID, int isAdmin) {
        relationDao.insertRelation(userID, doorID, isAdmin);
    }
}
