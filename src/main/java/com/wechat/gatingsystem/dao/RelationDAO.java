package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.Relation;

/**
 * Created by never on 2016/8/11.
 */
public interface RelationDAO {

    Relation selectByRelationID(int relationID);
}
