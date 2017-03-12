package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.po.Relation;

import java.util.HashMap;
import java.util.List;


/**
 * Created by never on 2016/8/11.
 */
public interface RelationDAO {

//    Relation selectByRelationID(int relationID);
//
//    //通过open_record里边的door_id，查看这扇门的开门记录。
//    //opend_record(relation_id) -> relation(user_id、door_id) -> user_info(user_name、user_phone)、door_info(door_name)
//    List<HashMap<Object, Object>> selectRecordByRelation(Integer doorId);
//
//    //传入user_id, door_id来对relation表进行更新
//    void insertRelation(Integer userID, Integer doorID, Integer isAdmin);
//
//    //根据door_id查找userID的信息
//    List<Relation> findUserIdInRelation(Integer doorId);
//
//    //删除用户之后要修改relation表
//    void deleteRelationByDoorUserId(Integer userId, Integer doorId);

    //插入相应的关系记录
    void insertRelation(Relation relation);

    //通过userID， doorID查找关系记录
    Relation findReByUDid(int uId, int dId);

    //删除relation
    void deleteRelation(int dId);

    //根据uID查找relation
    List<Relation> selectByUid(int uId);

    //根据dID查找relation
    List<Relation> selectByDid(int dId);

    //根据uId, dId删除relation
    void deleteReByUDid(int uId, int dId);
}
