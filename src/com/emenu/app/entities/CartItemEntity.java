package com.emenu.app.entities;

import java.io.Serializable;

public class CartItemEntity implements Serializable{
	private String cartTitle;
	private String cartPrice;
	private String cartPicUrl;
	private String cartItemID;
	private int cartAmount;
		

	public CartItemEntity(String cartTitle, String cartPrice,
			String cartPicUrl, String cartItemID, int cartAmount) {
		super();
		this.cartTitle = cartTitle;
		this.cartPrice = cartPrice;
		this.cartPicUrl = cartPicUrl;
		this.cartItemID = cartItemID;
		this.cartAmount = cartAmount;
	}
	public String getCartTitle() {
		return cartTitle;
	}
	public void setCartTitle(String cartTitle) {
		this.cartTitle = cartTitle;
	}
	public String getCartPrice() {
		return cartPrice;
	}
	public void setCartPrice(String cartPrice) {
		this.cartPrice = cartPrice;
	}
	public String getCartPicUrl() {
		return cartPicUrl;
	}
	public void setCartPicUrl(String cartPicUrl) {
		this.cartPicUrl = cartPicUrl;
	}
	public String getCartItemID() {
		return cartItemID;
	}
	public void setCartItemID(String cartItemID) {
		this.cartItemID = cartItemID;
	}
	public int getCartAmount() {
		return cartAmount;
	}
	public void setCartAmount(int cartAmount) {
		this.cartAmount = cartAmount;
	}
	
}
