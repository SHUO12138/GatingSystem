package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.OpenRecord;
import com.wechat.gatingsystem.po.Relation;

/**
 * Created by never on 2016/8/11.
 */
public interface OpenRecordDAO {

    //删除一条开门记录
    void deleteRecord(int reId);

    //添加一条开门记录
    void addOpenRecord(OpenRecord record);
}
