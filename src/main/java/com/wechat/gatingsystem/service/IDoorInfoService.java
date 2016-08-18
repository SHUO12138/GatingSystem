package com.wechat.gatingsystem.service;

import java.util.List;
import com.wechat.gatingsystem.po.DoorInfo;

/**
 * Created by never on 2016/8/10.
 */
public interface IDoorInfoService {

    List<DoorInfo> findAll();

    DoorInfo finddoorByID(int doorID);

    void insertDoorInfo(DoorInfo doorInfo);

    void deleteDoorByName(String name);

    /*void updateDoorInfoByID(DoorInfo doorInfo);*/
}
