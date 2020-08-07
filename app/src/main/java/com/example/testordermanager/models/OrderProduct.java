package com.example.testordermanager.models;

public class OrderProduct {
	private int idOrder;
	private int idProduct;
	private int count;
	private int priceSell;

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPriceSell() {
		return priceSell;
	}

	public void setPriceSell(int priceSell) {
		this.priceSell = priceSell;
	}
}

