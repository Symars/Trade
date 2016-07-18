package com.syhorde.gametime.dao;

import com.syhorde.gametime.vo.MyTrade;

public interface MyTradeDao {
	public MyTrade getMyTrade(String userCode);
	public void insertMyTrade(MyTrade mytrade);
}
