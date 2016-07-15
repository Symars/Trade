package com.syhorde.gametime.dao;

import com.syhorde.gametime.vo.MyWallet;

public interface MyWalletDao {
	public MyWallet getMyWallet(String userCode);
	
	public void updateMyWallet(MyWallet myWallet);
}
