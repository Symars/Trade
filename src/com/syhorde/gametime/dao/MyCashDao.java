package com.syhorde.gametime.dao;

import com.syhorde.gametime.vo.MyCash;

public interface MyCashDao {
	public MyCash getMyCash(String userCode);
	public void insertMyCash(MyCash mycash);
}
