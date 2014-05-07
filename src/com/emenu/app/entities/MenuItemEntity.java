package com.emenu.app.entities;

public class MenuItemEntity {
	private String itemTitle;
	private String itemText;
	private String itemPicUrl;
	public MenuItemEntity(String itemTitle,String itemText,String itemPicUrl)
	{
		this.itemPicUrl=itemPicUrl;
		this.itemText = itemText;
		this.itemTitle = itemTitle;
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
}
