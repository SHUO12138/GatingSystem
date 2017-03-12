package com.wechat.gatingsystem.po;

import java.util.Date;

/**
 * Created by never on 2016/8/11.
 */
public class OpenRecord {

    public int recordID;
    public int relationID;
    public Date recordTime;

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getRelationID() {
        return relationID;
    }

    public void setRelationID(int relationID) {
        this.relationID = relationID;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
