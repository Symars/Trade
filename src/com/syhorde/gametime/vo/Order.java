package com.syhorde.gametime.vo;

import com.google.gson.annotations.SerializedName;

public class Order {
	
	@SerializedName("OrderCode")
	private String orderCode;
	
	@SerializedName("OrderBatch")
	private String orderBatch;
	
	@SerializedName("OrderName")
	private String orderName;
	
	@SerializedName("StartDate")
	private String orderStartDate;
	
	@SerializedName("EndDate")
	private String orderEndDate;
	
	@SerializedName("OrderDate")
	private String orderDate;
	
	@SerializedName("UserCode")
	private String userCode;
	
	@SerializedName("UserName")
	private String userName;
	
	@SerializedName("GoodsCode")
	private String goodsCode;
	
	@SerializedName("Price")
	private Double OrderPrice;
	
	@SerializedName("Num")
	private int OrderNum;
	
	@SerializedName("CouponCode")
	private String CouponCode;
	
	@SerializedName("AddressCode")
	private String AddressCode;
	
	@SerializedName("PriceCut")
	private Double OrderPriceCut;
	
	@SerializedName("ProductCode")
	private String ProductCode;
	
	@SerializedName("ProductItemCode")
	private String ProductItemCode;
	
	@SerializedName("Type")
	private String OrderType;
	
	@SerializedName("OrderNote")
	private String OrderNote;
	
	@SerializedName("OrderStatus")
	private String OrderStatus;

	/**
	 * @return the orderBatch
	 */
	public String getOrderBatch() {
		return orderBatch;
	}

	/**
	 * @param orderBatch the orderBatch to set
	 */
	public void setOrderBatch(String orderBatch) {
		this.orderBatch = orderBatch;
	}

	/**
	 * @return the orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * @return the orderName
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @param orderName the orderName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * @return the orderStartDate
	 */
	public String getOrderStartDate() {
		return orderStartDate;
	}

	/**
	 * @param orderStartDate the orderStartDate to set
	 */
	public void setOrderStartDate(String orderStartDate) {
		this.orderStartDate = orderStartDate;
	}

	/**
	 * @return the orderEndDate
	 */
	public String getOrderEndDate() {
		return orderEndDate;
	}

	/**
	 * @param orderEndDate the orderEndDate to set
	 */
	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the goodsCode
	 */
	public String getGoodsCode() {
		return goodsCode;
	}

	/**
	 * @param goodsCode the goodsCode to set
	 */
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	/**
	 * @return the orderPrice
	 */
	public Double getOrderPrice() {
		return OrderPrice;
	}

	/**
	 * @param orderPrice the orderPrice to set
	 */
	public void setOrderPrice(Double orderPrice) {
		OrderPrice = orderPrice;
	}

	/**
	 * @return the orderNum
	 */
	public int getOrderNum() {
		return OrderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(int orderNum) {
		OrderNum = orderNum;
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
	 * @return the addressCode
	 */
	public String getAddressCode() {
		return AddressCode;
	}

	/**
	 * @param addressCode the addressCode to set
	 */
	public void setAddressCode(String addressCode) {
		AddressCode = addressCode;
	}

	/**
	 * @return the orderPriceCut
	 */
	public Double getOrderPriceCut() {
		return OrderPriceCut;
	}

	/**
	 * @param orderPriceCut the orderPriceCut to set
	 */
	public void setOrderPriceCut(Double orderPriceCut) {
		OrderPriceCut = orderPriceCut;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return ProductCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}

	/**
	 * @return the productItemCode
	 */
	public String getProductItemCode() {
		return ProductItemCode;
	}

	/**
	 * @param productItemCode the productItemCode to set
	 */
	public void setProductItemCode(String productItemCode) {
		ProductItemCode = productItemCode;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return OrderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	/**
	 * @return the orderNote
	 */
	public String getOrderNote() {
		return OrderNote;
	}

	/**
	 * @param orderNote the orderNote to set
	 */
	public void setOrderNote(String orderNote) {
		OrderNote = orderNote;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return OrderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
}
