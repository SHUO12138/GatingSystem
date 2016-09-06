package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.Relation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by never on 2016/8/11.
 */
public interface IRelationService {

    Relation selectByRelationID(int relationID);

    List<HashMap<Object, Object>> selectRecordByRelation(Integer doorId);

    void insertRelation(int userID, int doorID, int isAdmin);
}
