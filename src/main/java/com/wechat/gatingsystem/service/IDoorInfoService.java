package com.wechat.gatingsystem.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wechat.gatingsystem.po.DoorInfo;

/**
 * Created by never on 2016/8/10.
 */
public interface IDoorInfoService {

    List<DoorInfo> findAll();

    DoorInfo finddoorByID(int doorID);

    void insertDoorInfo(DoorInfo doorInfo);

    void deleteByDoorName(String doorName);

    List<DoorInfo> finddoorByName(String name);

    Integer findDoorId(String doorName);

    Integer idMaxRecord();
}
