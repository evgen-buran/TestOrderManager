package com.example.testordermanager.models;

public class Product {
	private int idProduct;
	private String nameProduct;
	private int priceProduct; //цена в копейках
//	private String descriptionProduct; - нет в базе

	public Product() {
		setNameProduct("Чайник");
		setPriceProduct(100);
//		this.descriptionProduct = descriptionProduct;
	}

	public Product(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public int getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(int priceProduct) {
		this.priceProduct = priceProduct;
	}

//	public String getDescriptionProduct() {
//		return descriptionProduct;
//	}
//
//	public void setDescriptionProduct(String descriptionProduct) {
//		this.descriptionProduct = descriptionProduct;
//	}
}

