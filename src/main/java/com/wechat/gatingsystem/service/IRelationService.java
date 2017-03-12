package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.po.Relation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/11.
 */
public interface IRelationService {

    void insertRelation(Relation relation);

    Relation findReByUDid(int uId, int dId);

    void deleteRelation(int dId);

    List<Relation> selectByUid(int uId);

    List<Relation> selectByDid(int dId);

    void deleteReByUDid(int uId, int dId);
}
