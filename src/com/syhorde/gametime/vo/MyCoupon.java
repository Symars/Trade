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
	private double CouponAmount;
	@SerializedName("MyCouponenddate")
	private String MyCouponEndDate;
	@SerializedName("MyCouponcrtdate")
	private String MyCouponCrtDate;
	@SerializedName("MyCouponDesc")
	private String MyCouponNote;
	@SerializedName("MyCouponState")
	private String MyCouponType;
	/**
	 * @return the myCouponCode
	 */
	public String getMyCouponCode() {
		return MyCouponCode;
	}
	/**
	 * @param myCouponCode the myCouponCode to set
	 */
	public void setMyCouponCode(String myCouponCode) {
		MyCouponCode = myCouponCode;
	}
	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return UserCode;
	}
	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}
	/**
	 * @return the couponCode
	 */
	public String getCouponCode() {
		return CouponCode;
	}
	/**
	 * @param couponCode the couponCode to set
	 */
	public void setCouponCode(String couponCode) {
		CouponCode = couponCode;
	}
	/**
	 * @return the couponAmount
	 */
	public double getCouponAmount() {
		return CouponAmount;
	}
	/**
	 * @param couponAmount the couponAmount to set
	 */
	public void setCouponAmount(double couponAmount) {
		CouponAmount = couponAmount;
	}
	/**
	 * @return the myCouponEndDate
	 */
	public String getMyCouponEndDate() {
		return MyCouponEndDate;
	}
	/**
	 * @param myCouponEndDate the myCouponEndDate to set
	 */
	public void setMyCouponEndDate(String myCouponEndDate) {
		MyCouponEndDate = myCouponEndDate;
	}
	/**
	 * @return the myCouponCrtDate
	 */
	public String getMyCouponCrtDate() {
		return MyCouponCrtDate;
	}
	/**
	 * @param myCouponCrtDate the myCouponCrtDate to set
	 */
	public void setMyCouponCrtDate(String myCouponCrtDate) {
		MyCouponCrtDate = myCouponCrtDate;
	}
	/**
	 * @return the myCouponNote
	 */
	public String getMyCouponNote() {
		return MyCouponNote;
	}
	/**
	 * @param myCouponNote the myCouponNote to set
	 */
	public void setMyCouponNote(String myCouponNote) {
		MyCouponNote = myCouponNote;
	}
	/**
	 * @return the myCouponType
	 */
	public String getMyCouponType() {
		return MyCouponType;
	}
	/**
	 * @param myCouponType the myCouponType to set
	 */
	public void setMyCouponType(String myCouponType) {
		MyCouponType = myCouponType;
	}
	
	
}
