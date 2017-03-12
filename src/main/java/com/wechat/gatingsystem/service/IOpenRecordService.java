package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.OpenRecord;
import com.wechat.gatingsystem.po.Relation;

/**
 * Created by never on 2016/8/11.
 */
public interface IOpenRecordService {

    void deleteRecord(int reId);

    void addOpenRecord(OpenRecord record);
}
