package com.syhorde.gametime.dao;

import com.syhorde.gametime.vo.UserVIP;

public interface UserVIPDao {
	public int getUserVIP(String userCode);
	public int getCurrentRentOrder(String userCode);
	public void insertUserVIP(UserVIP userVIP);
}
