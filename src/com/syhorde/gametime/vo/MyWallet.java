package com.syhorde.gametime.vo;

import com.google.gson.annotations.SerializedName;

public class MyWallet {
	
	@SerializedName("WalletCode") 
	private String walletCode;
	@SerializedName("UserCode")
	private String userCode;
	@SerializedName("WalletAmount")
	private double walletAmount;
	@SerializedName("WalletPledge")
	private double walletPledge;
	@SerializedName("WalletCrtDate")
	private String walletCrtDate;
	@SerializedName("WalletUpdDate")
	private String walletUpdDate;
	/**
	 * @return the walletCode
	 */
	public String getWalletCode() {
		return walletCode;
	}
	/**
	 * @param walletCode the walletCode to set
	 */
	public void setWalletCode(String walletCode) {
		this.walletCode = walletCode;
	}
	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * @return the walletAmount
	 */
	public double getWalletAmount() {
		return walletAmount;
	}
	/**
	 * @param walletAmount the walletAmount to set
	 */
	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}
	/**
	 * @return the walletPledge
	 */
	public double getWalletPledge() {
		return walletPledge;
	}
	/**
	 * @param walletPledge the walletPledge to set
	 */
	public void setWalletPledge(double walletPledge) {
		this.walletPledge = walletPledge;
	}
	/**
	 * @return the walletCrtDate
	 */
	public String getWalletCrtDate() {
		return walletCrtDate;
	}
	/**
	 * @param walletCrtDate the walletCrtDate to set
	 */
	public void setWalletCrtDate(String walletCrtDate) {
		this.walletCrtDate = walletCrtDate;
	}
	/**
	 * @return the walletUpdDate
	 */
	public String getWalletUpdDate() {
		return walletUpdDate;
	}
	/**
	 * @param walletUpdDate the walletUpdDate to set
	 */
	public void setWalletUpdDate(String walletUpdDate) {
		this.walletUpdDate = walletUpdDate;
	}
	
}
