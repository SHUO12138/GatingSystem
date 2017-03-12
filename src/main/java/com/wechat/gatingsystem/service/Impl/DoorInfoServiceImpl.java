package com.wechat.gatingsystem.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.dao.DoorInfoDAO;
import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.service.IDoorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by never on 2016/8/10.
 */

@Repository("doorInfoService")
public class DoorInfoServiceImpl implements IDoorInfoService {

    @Autowired
    private DoorInfoDAO doorInfoDAO;

    @Override
    public void insertDoorInfo(DoorInfo dInfo) {
        doorInfoDAO.insertDoorInfo(dInfo);
    }

    @Override
    public DoorInfo findByName(String dName) {
        DoorInfo doorInfo = doorInfoDAO.findByName(dName);
        return doorInfo;
    }

    @Override
    public void deleteDoor(String dName) {
        doorInfoDAO.deleteDoor(dName);
    }

    @Override
    public DoorInfo selectDoorById(int dId) {

        DoorInfo doorInfo = doorInfoDAO.selectDoorById(dId);
        return doorInfo;
    }
}
