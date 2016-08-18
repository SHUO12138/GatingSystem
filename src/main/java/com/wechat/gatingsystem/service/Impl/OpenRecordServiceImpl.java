package com.wechat.gatingsystem.service.Impl;

import com.wechat.gatingsystem.dao.OpenRecordDAO;
import com.wechat.gatingsystem.po.OpenRecord;
import com.wechat.gatingsystem.service.IOpenRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by never on 2016/8/11.
 *
 */

@Service("openRecordServiceImpl")
public class OpenRecordServiceImpl implements IOpenRecordService{

    @Autowired
    private OpenRecordDAO openRecordDao;

    @Override
    public OpenRecord selectByRecordID(int recordID) {

        OpenRecord openDoorRecord;
        openDoorRecord = openRecordDao.selectByRecordID(recordID);
        return openDoorRecord;
    }

}
