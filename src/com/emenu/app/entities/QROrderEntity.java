package com.emenu.app.entities;

import java.io.Serializable;

public class QROrderEntity implements Serializable{
	private String restaurantName;
	private String restaurantID;
	private String tableID;
	private String tableStatus;
	private String orderID;
	private String qrCode;
	

	public QROrderEntity(String restaurantName, String restaurantID,
			String tableID, String tableStatus, String orderID, String qrCode) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantID = restaurantID;
		this.tableID = tableID;
		this.tableStatus = tableStatus;
		this.orderID = orderID;
		this.qrCode = qrCode;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(String restaurantID) {
		this.restaurantID = restaurantID;
	}
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public String getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
}