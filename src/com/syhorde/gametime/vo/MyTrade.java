package com.syhorde.gametime.vo;

import com.google.gson.annotations.SerializedName;

public class MyTrade {
	@SerializedName("TradeCode") 
	private String TradeCode;
	@SerializedName("UserCode")
	private String UserCode;
	@SerializedName("OrderCode")
	private String OrderCode;
	@SerializedName("TradeType")
	private String TradeType;
	@SerializedName("TradeAmount")
	private Double TradeAmount;
	@SerializedName("StartDate")
	private String TradeCrtDate;
	@SerializedName("EndDate")
	private String TradeUpdDate;
	public String getTradeCode(){
		return TradeCode;
	}
	public void setTradeCode(String tradecode){
		this.TradeCode=tradecode;
	}
	public String getUserCode(){
		return UserCode;
	}
	public void setUserCode(String usercode){
		this.UserCode=usercode;
	}
	public String getOrderCode(){
		return OrderCode;
	}
	public void setOrderCode(String ordercode){
		this.OrderCode=ordercode;
	}
	public String getTradeType(){
		return TradeType;
	}
	public void setTradeType(String tradetype){
		this.TradeType=tradetype;
	}
	public Double getTradeAmount(){
		return TradeAmount;
	}
	public void setTradeAmount(Double tradeamount){
		this.TradeAmount=tradeamount;
	}
	public String getTradeCrtDate(){
		return TradeCrtDate;
	}
	public void setTradeCrtDate(String tradecrtdate){
		this.TradeCrtDate=tradecrtdate;
	}
	public String getTradeUpdDate(){
		return TradeUpdDate;
	}
	public void setTradeUpdDate(String tradeupddate){
		this.TradeUpdDate=tradeupddate;
	}
	
}
