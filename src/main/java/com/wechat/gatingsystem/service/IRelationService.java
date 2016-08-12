package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.Relation;

/**
 * Created by never on 2016/8/11.
 */
public interface IRelationService {

    Relation selectByRelationID(int relationID);
}
