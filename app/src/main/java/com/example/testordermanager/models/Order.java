package com.example.testordermanager.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private int idOrder;
	private int idCustomer;
	private int idStatus;
	private Date dateStartOrder;
	private Date dateFinishOrder;

	public Order() {
		dateStartOrder = new Date();
		this.idStatus = 1;

	}
	public Order(int idOrder) {
		this.idOrder = idOrder;

	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Date getDateStartOrder() {
		return dateStartOrder;
	}

	public void setDateStartOrder(Date dateStartOrder) {
		this.dateStartOrder = dateStartOrder;
	}

	public Date getDateFinishOrder() {
		return dateFinishOrder;
	}

	public void setDateFinishOrder(Date dateFinishOrder) {
		this.dateFinishOrder = dateFinishOrder;
	}

	public String getShortDate(Date date) {
		return new SimpleDateFormat("dd MMM yyyy").format(date);
	}


}


