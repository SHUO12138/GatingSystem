package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.Relation;

import java.util.HashMap;
import java.util.List;


/**
 * Created by never on 2016/8/11.
 */
public interface RelationDAO {

    Relation selectByRelationID(int relationID);

    //通过open_record里边的door_id，查看这扇门的开门记录。
    //opend_record(relation_id) -> relation(user_id、door_id) -> user_info(user_name、user_phone)、door_info(door_name)
    List<HashMap<Object, Object>> selectRecordByRelation(Integer doorId);

}
