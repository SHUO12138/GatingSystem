package com.wechat.gatingsystem.service.Impl;

import com.wechat.gatingsystem.dao.RelationDAO;
import com.wechat.gatingsystem.po.Relation;
import com.wechat.gatingsystem.service.IRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        System.out.println(relation.getDoorID());
        return relation;
    }

/*    @Override
    public Relation selectByRelationID(int relationID) {

        Relation relation = new Relation();

        relation.setRelationID(relationID);
        relation.relationID = relation.getRelationID();
        //judge
        relation = relationDao.selectByRelationID(relation.relationID);

        System.out.println(relation.doorID);
        return relation;
    }*/
}
