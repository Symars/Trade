package com.syhorde.gametime.vo;

import com.google.gson.annotations.SerializedName;

public class  Category{
	
	@SerializedName("ID")
	private String ID;
	
	@SerializedName("Name")
	private String Name;
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	
}
