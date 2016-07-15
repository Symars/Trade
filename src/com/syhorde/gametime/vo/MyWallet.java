package com.syhorde.gametime.vo;

import com.google.gson.annotations.SerializedName;

public class MyWallet {
	@SerializedName("WalletCode") 
	private String walletCode;
	@SerializedName("UserCode")
	private String userCode;
	@SerializedName("WalletAmount")
	private String walletAmount;
	@SerializedName("WalletPledge")
	private String walletPledge;
	@SerializedName("WalletCrtDate")
	private String walletCrtDate;
	@SerializedName("WalletUpdDate")
	private String walletUpdDate;
	public String getwalletCode(){
		return walletCode;
	}
	public void setwalletCode(String walletcode){
		this.walletCode=walletcode;
	}
	public String getuserCode(){
		return userCode;
	}
	public void setuserCode(String usercode){
		this.userCode=usercode;
	}
	public String getwalletAmount(){
		return walletAmount;
	}
	public void setwalletAmount(String walletamount){
		this.walletAmount=walletamount;
	}
	public String getwalletPledge(){
		return walletPledge;
	}
	public void setwalletPledge(String walletpledge){
		this.walletPledge=walletpledge;
	}
	public String getwalletCrtDate(){
		return walletCrtDate;
	}
	public void setwalletCrtDate(String walletcrtdate){
		this.walletCrtDate=walletcrtdate;
	}
	public String getwalletUpdDate(){
		return walletUpdDate;
	}
	public void setwalletUpdDate(String walletupddate){
		this.walletUpdDate=walletupddate;
	}
	
}
