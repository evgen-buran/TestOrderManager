package com.example.testordermanager.datebase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.testordermanager.datebase.OrderDBScheme.TableCustomers;
import com.example.testordermanager.datebase.OrderDBScheme.TableOrders;
import com.example.testordermanager.datebase.OrderDBScheme.TableProducts;
import com.example.testordermanager.datebase.OrderDBScheme.TableStatus;
import com.example.testordermanager.models.Customer;
import com.example.testordermanager.models.Order;
import com.example.testordermanager.models.Product;
import com.example.testordermanager.models.StatusOrder;

import java.util.Date;

public class AppCursorWrapper extends CursorWrapper {

	public AppCursorWrapper(Cursor cursor) {
		super(cursor);
	}

	//получение объекта CUSTOMER из строки курсора
	public Customer getCustomer() {
		int idCustomer = getInt(getColumnIndex(TableCustomers.Cols.ID_CUSTOMER));
		String nameCustomer = getString(getColumnIndex(TableCustomers.Cols.NAME_CUSTOMER));
		String phoneCustomer = getString(getColumnIndex(TableCustomers.Cols.PHONE_CUSTOMER));

		Customer customer = new Customer(idCustomer);
		customer.setCustomerName(nameCustomer);
		customer.setCustomerPhone(phoneCustomer);
		return customer;
	}

	public String getCustomerById() {
		String name = getString(getColumnIndex(TableCustomers.Cols.NAME_CUSTOMER));
		return name;
	}

	//получение объекта PRODUCT из строки курсора
	public Product getProduct() {
		int idProduct = getInt(getColumnIndex(TableProducts.Cols.ID_PRODUCT));
		String nameProduct = getString(getColumnIndex(TableProducts.Cols.NAME_PRODUCT));
		int priceProduct = getInt(getColumnIndex(TableProducts.Cols.PRICE_PRODUCT));

		Product product = new Product(idProduct);
		product.setNameProduct(nameProduct);
		product.setPriceProduct(priceProduct);
		return product;
	}

	//получение объекта STATUS из строки курсора
	public StatusOrder getStatusOrder() {
		int idStatus = getInt(getColumnIndex(TableStatus.Cols.ID_STATUS));
		String nameStatus = getString(getColumnIndex(TableStatus.Cols.NAME_STATUS));

		StatusOrder statusOrder = new StatusOrder(idStatus);
		statusOrder.setNameStatus(nameStatus);
		return statusOrder;
	}

	public String getStatusById() {
		String nameStatus = getString(getColumnIndex(TableStatus.Cols.NAME_STATUS));
		return nameStatus;
	}

	//получение объекта ORDER из строки курсора
	public Order getOrder() {
		int idOrder = getInt(getColumnIndex(TableOrders.Cols.ID_ORDER));
		int idStatus = getInt(getColumnIndex(TableOrders.Cols.ID_STATUS));
		long dateStart = getLong(getColumnIndex(TableOrders.Cols.DATE_START_ORDER));

		Order order = new Order(idOrder);
		order.setIdStatus(idStatus);
		order.setDateStartOrder(new Date(dateStart));
		return order;
	}



}
