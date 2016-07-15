package com.syhorde.gametime.vo;

import com.google.gson.annotations.SerializedName;

public class MyCoupon {
	@SerializedName("MyCouponCode") 
	private String MyCouponCode;
	@SerializedName("MyCouponUserCode")
	private String UserCode;
	@SerializedName("MyCouponName") 
	private String UserName;
	@SerializedName("CouponCode") 
	private String CouponCode;
	@SerializedName("MyCouponAmount")
	private String MyCouponCount;
	@SerializedName("MyCouponenddate")
	private String MyCouponEndDate;
	@SerializedName("MyCouponcrtdate")
	private String MyCouponCrtDate;
	@SerializedName("MyCouponDesc")
	private String MyCouponNote;
	@SerializedName("MyCouponState")
	private String MyCouponType;
	public String getMyCouponCode(){
		return MyCouponCode;
	}
	public void setMyCouponCode(String MyCouponCode){
		this.MyCouponCode=MyCouponCode;
	}
	public String getUserCode(){
		return UserCode;
	}
	public void setUserCode(String UserCode){
		this.UserCode=UserCode;
	}
	public String getUserName(){
		return UserName;
	}
	public void setUserName(String UserName){
		this.UserName=UserName;
	}
	public String getCouponCode(){
		return CouponCode;
	}
	public void setCouponCode(String CouponCode){
		this.CouponCode=CouponCode;
	}
	public String getMyCouponCount(){
		return MyCouponCount;
	}
	public void setMyCouponCount(String MyCouponCount){
		this.MyCouponCount=MyCouponCount;
	}
	public String getMyCouponEndDate(){
		return MyCouponEndDate;
	}
	public void setMyCouponEndDate(String MyCouponEndDate){
		this.MyCouponEndDate=MyCouponEndDate;
	}
	public String getMyCouponCrtDate(){
		return MyCouponCrtDate;
	}
	public void setMyCouponCrtDate(String MyCouponCrtDate){
		this.MyCouponCrtDate=MyCouponCrtDate;
	}
	public String getMyCouponNote(){
		return MyCouponNote;
	}
	public void setMyCouponNote(String MyCouponNote){
		this.MyCouponNote=MyCouponNote;
	}
	public String getMyCouponType(){
		return MyCouponType;
	}
	public void setMyCouponType(String MyCouponType){
		this.MyCouponType=MyCouponType;
	}
	
}
