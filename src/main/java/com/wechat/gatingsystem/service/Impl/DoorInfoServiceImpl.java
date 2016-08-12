package com.wechat.gatingsystem.service.Impl;

import java.util.ArrayList;
import java.util.List;
import com.wechat.gatingsystem.dao.DoorInfoDAO;
import com.wechat.gatingsystem.po.DoorInfo;
import com.wechat.gatingsystem.service.IDoorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by never on 2016/8/10.
 *
 */

@Repository("doorInfoService")
public class DoorInfoServiceImpl implements IDoorInfoService{

    @Autowired
    private DoorInfoDAO doorInfoDao;

    private List<DoorInfo> doorInfoList = new ArrayList<DoorInfo>();

    @Override
    public List<DoorInfo> findAll() {

        doorInfoList = doorInfoDao.findAll();
        for(int i = 0; i < doorInfoList.size(); i++) {
            System.out.println(doorInfoList.get(i).doorName);
        }
        return doorInfoList;
    }

    @Override
    public DoorInfo finddoorByID(int doorID) {

        DoorInfo door = new DoorInfo();

        door.setDoorID(doorID);
        door.doorID = door.getDoorID();
        door = doorInfoDao.finddoorByID(door.doorID);
        return door;
    }

    @Override
    public void insertDoorInfo(DoorInfo doorInfo) {

        //clear Lilst
        doorInfoList.clear();
        doorInfoList = findAll();

        //while flg is 'true',can insert doorInfo
        boolean flg = true;
        for(int i = 0; i < doorInfoList.size(); i++){
            if(doorInfoList.get(i).doorName.equals(doorInfo.doorName)){
                flg = false;
                break;
            }
        }
        if(flg) {
            doorInfoDao.insertDoorInfo(doorInfo);
            System.out.println("Insert successfully!!");
        }
        else{
            System.out.println("This door already exists!");
        }

    }

    @Override
    public void delteDoorByName(String name) {

        DoorInfo doorInfo = new DoorInfo();
        doorInfo.setDoorName(name);
        doorInfo.doorName = doorInfo.getDoorName();

        //clear Lilst
        doorInfoList.clear();
        doorInfoList = findAll();

        //if flg is false represents the door don't exist
        boolean flg = false;
        for(int i = 0; i < doorInfoList.size(); i++){
            if(doorInfoList.get(i).doorName.equals(doorInfo.doorName)){
                flg = true;
                break;
            }
        }
        if(flg){
            doorInfoDao.deleteDoorByName(name);
            System.out.println("delete successfully!!");
        }
        else{
            System.out.println("This door don't exist!");
        }
    }

}
