package com.wechat.gatingsystem.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.po.DoorInfo;

/**
 * Created by never on 2016/8/10.
 */
public interface IDoorInfoService {

    void insertDoorInfo(DoorInfo dInfo);

    DoorInfo findByName(String dName);

    void deleteDoor(String dName);

    DoorInfo selectDoorById(int dId);

}
