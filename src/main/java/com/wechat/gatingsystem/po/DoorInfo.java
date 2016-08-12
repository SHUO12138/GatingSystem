package com.wechat.gatingsystem.po;

import java.util.Date;

public class DoorInfo {

	public int doorID;
	public String doorName;
	public Date doorCreateTime;
	public String doorLocation;
	public String doorMoreInfo;

	public int getDoorID() {
		return doorID;
	}

	public void setDoorID(int doorID) {
		this.doorID = doorID;
	}

	public String getDoorName() {
		return doorName;
	}

	public void setDoorName(String doorName) {
		this.doorName = doorName;
	}

	public Date getDoorCreateTime() {
		return doorCreateTime;
	}

	public void setDoorCreateTime(Date doorCreateTime) {
		this.doorCreateTime = doorCreateTime;
	}

	public String getDoorLocation() {
		return doorLocation;
	}

	public void setDoorLocation(String doorLocation) {
		this.doorLocation = doorLocation;
	}

	public String getDoorMoreInfo() {
		return doorMoreInfo;
	}

	public void setDoorMoreInfo(String doorMoreInfo) {
		this.doorMoreInfo = doorMoreInfo;
	}
}
