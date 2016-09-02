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
    private DoorInfoDAO doorInfoDao;

    @Override
    public List<DoorInfo> findAll() {

        List<DoorInfo> doorInfoList = new ArrayList<DoorInfo>();
        doorInfoList = doorInfoDao.findAll();
        return doorInfoList;
    }

    @Override
    public DoorInfo finddoorByID(int doorID) {

        DoorInfo door = doorInfoDao.finddoorByID(doorID);
        return door;
    }

    @Override
    public void insertDoorInfo(DoorInfo doorInfo) {

        doorInfoDao.insertDoorInfo(doorInfo);
        return;

    }

    @Override
    public void deleteByDoorName(String doorName) {
        doorInfoDao.deleteByDoorName(doorName);
    }

}
