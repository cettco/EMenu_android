package com.emenu.app.entities;

public class RestaurantItemEntity {
	private String url;
	private String name;
	private String address;
	public RestaurantItemEntity(String url,String name,String address)
	{
		this.url = url;
		this.name = name;
		this.address = address;
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
	
}
