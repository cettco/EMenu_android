package com.emenu.app.entities;

import java.io.Serializable;

public class MenuItemEntity implements Serializable{
	private String itemTitle;
	private String itemText;
	private String itemPicUrl;
	private String itemID;
	private String itemUnit;
	private String description;
	

	public MenuItemEntity(String itemTitle, String itemText, String itemPicUrl,
			String itemID, String itemUnit, String description) {
		super();
		this.itemTitle = itemTitle;
		this.itemText = itemText;
		this.itemPicUrl = itemPicUrl;
		this.itemID = itemID;
		this.itemUnit = itemUnit;
		this.description = description;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getItemText() {
		return itemText;
	}
	public void setItemText(String itemText) {
		this.itemText = itemText;
	}
	public String getItemPicUrl() {
		return itemPicUrl;
	}
	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
