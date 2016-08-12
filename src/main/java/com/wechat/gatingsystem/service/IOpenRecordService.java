package com.wechat.gatingsystem.service;

import com.wechat.gatingsystem.po.OpenRecord;

/**
 * Created by never on 2016/8/11.
 */
public interface IOpenRecordService {

    OpenRecord selectByRecordID(int recordID);
}
