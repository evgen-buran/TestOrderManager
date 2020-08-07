package com.example.testordermanager.models;

import java.util.Random;

public class Customer {
	private int idCustomer;
	private String customerName;
	private String customerLastName;
	private String customerMiddleName;
	private String customerPhone;
	private String customerEmail;



	public Customer() {
		this.customerName = "TEST_NAME";
		this.customerPhone = "TEST_PHONE";
	}

	public Customer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


}

