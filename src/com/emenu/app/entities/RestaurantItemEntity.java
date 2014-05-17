package com.emenu.app.entities;

import java.io.Serializable;

public class RestaurantItemEntity implements Serializable{
	private String url;
	private String name;
	private String address;
	private String type;
	private String restID;
	private String menuID;
	private String description;
	private String phone;
	


	public RestaurantItemEntity(String url, String name, String address,
			String type, String restID, String menuID, String description,
			String phone) {
		super();
		this.url = url;
		this.name = name;
		this.address = address;
		this.type = type;
		this.restID = restID;
		this.menuID = menuID;
		this.description = description;
		this.phone = phone;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRestID() {
		return restID;
	}
	public void setRestID(String restID) {
		this.restID = restID;
	}
	public String getMenuID() {
		return menuID;
	}
	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
