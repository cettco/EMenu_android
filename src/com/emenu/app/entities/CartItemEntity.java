package com.emenu.app.entities;

import java.io.Serializable;

public class CartItemEntity implements Serializable{
	private String cartTitle;
	private String cartPrice;
	private String cartPicUrl;
	private int cartAmount;
		
	public CartItemEntity(String cartTitle, String cartPrice,
			String cartPicUrl, int cartAmount) {
		super();
		this.cartTitle = cartTitle;
		this.cartPrice = cartPrice;
		this.cartPicUrl = cartPicUrl;
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
	public int getcartAmount() {
		return cartAmount;
	}
	public void setcartAmount(int cartAmount) {
		this.cartAmount = cartAmount;
	}
	
}
