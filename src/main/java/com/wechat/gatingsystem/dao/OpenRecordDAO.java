package com.wechat.gatingsystem.dao;

import com.wechat.gatingsystem.po.OpenRecord;

/**
 * Created by never on 2016/8/11.
 */
public interface OpenRecordDAO {

    OpenRecord selectByRecordID(int recordID);
}
