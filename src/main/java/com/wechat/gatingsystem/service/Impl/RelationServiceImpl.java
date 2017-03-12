package com.wechat.gatingsystem.service.Impl;

import com.wechat.gatingsystem.dao.RelationDAO;
import com.wechat.gatingsystem.po.DoorInfo;
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
    public void insertRelation(Relation relation) {
        relationDao.insertRelation(relation);
    }

    @Override
    public Relation findReByUDid(int uId, int dId) {

        Relation relation = relationDao.findReByUDid(uId, dId);
        return relation;
    }

    @Override
    public void deleteRelation(int dId) {
        relationDao.deleteRelation(dId);
    }

    @Override
    public List<Relation> selectByUid(int uId) {
        List<Relation> relationList = relationDao.selectByUid(uId);
        return relationList;
    }

    @Override
    public List<Relation> selectByDid(int dId) {
        List<Relation> relationList = relationDao.selectByDid(dId);
        return relationList;
    }

    @Override
    public void deleteReByUDid(int uId, int dId) {
        relationDao.deleteReByUDid(uId, dId);
    }
}
