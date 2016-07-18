package com.syhorde.gametime.vo;

import com.google.gson.annotations.SerializedName;

public class MyCash {
	@SerializedName("CashCode")
	private String CashCode;
	@SerializedName("UserCode")
	private String UserCode;
	@SerializedName("CashType")
	private String CashType;
	@SerializedName("CashAmount")
	private String CashAmount;
	@SerializedName("CashDate")
	private String CashCrtDate;
	
	public String getCashCode(){
		return CashCode;
	}
	public void setCashCode(String cashcode){
		this.CashCode=cashcode;
	}
	public String getUserCode(){
		return UserCode;
	}
	public void setUserCode(String usercode){
		this.UserCode=usercode;
	}
	public String getCashType(){
		return CashType;
	}
	public void setCashType(String cashtype){
		this.CashType=cashtype;
	}
	public String getCashAmount(){
		return CashAmount;
	}
	public void setCashAmount(String cashamount){
		this.CashAmount=cashamount;
	}
	public String getCashCrtDate(){
		return CashCrtDate;
	}
	public void setCashCrtDate(String cashcrtdate){
		this.CashCrtDate=cashcrtdate;
	}
	
	
}
