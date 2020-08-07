package com.example.testordermanager.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testordermanager.datebase.AppCursorWrapper;
import com.example.testordermanager.datebase.OrderDBScheme.TableCustomers;
import com.example.testordermanager.datebase.SingleHelperDB;
import com.example.testordermanager.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerLab {
	private static CustomerLab customerLab;
	private Context mContext;
	private SQLiteDatabase dateBase;
	private SingleHelperDB singleHelperDB;

	// customers не нужен! т.к. каждый метод создает список сам. Список временный, заполняется из
	// БД и отдается по запросу. Больше нигде не используется!
	private List<Customer> customers;
	//	private long rowIdCustomer;


	public static CustomerLab get(Context context) {
		if (customerLab == null) {
			customerLab = new CustomerLab(context);
		}
		return customerLab;
	}

	private CustomerLab(Context context) {
		mContext = context.getApplicationContext();
		singleHelperDB = SingleHelperDB.getInstance(mContext);
		dateBase = singleHelperDB.getOrderDBHelper().getWritableDatabase();
	}

	//add new customer in DB
	public void addCustomer(Customer customer) {
		ContentValues values = getContentValues(customer);
		long rowIdCustomer = dateBase.insert(TableCustomers.TABLE_CUSTOMERS, null, values);
		customer.setIdCustomer((int) rowIdCustomer);
	}

	// delete customer from DB по ID переданного покупателя
	public void deleteCustomer(Customer customer) {
		String id = String.valueOf(customer.getIdCustomer());
		dateBase.delete(TableCustomers.TABLE_CUSTOMERS,
		                TableCustomers.Cols.ID_CUSTOMER + "=?",
		                new String[]{id});
	}

	//update user
	public void updateCustomer(Customer customer) {
		String idString = String.valueOf(customer.getIdCustomer());
		ContentValues values = getContentValues(customer);

		dateBase.update(TableCustomers.TABLE_CUSTOMERS, values,
		                TableCustomers.Cols.ID_CUSTOMER + "=?",
		                new String[]{idString});
	}


	//get customer by id
	//отправить в CustomerCursorWrapper курсов из метода queryCustomer
	public Customer getCustomer(int id) {
		String idString = String.valueOf(id);
		AppCursorWrapper cursorWrapper = queryCustomer(TableCustomers.Cols.ID_CUSTOMER + "=?", new String[]{idString});
		try {
			if (cursorWrapper.getCount() == 0) {
				return null;
			} else {
				cursorWrapper.moveToFirst();
				return cursorWrapper.getCustomer();
			}
		} finally {
			cursorWrapper.close();
		}
	}

	//get list of customers
	//сделать запрос (queryCustomers по всем полям)
	//готовый курсор прогонять по строкам из них создавать объекты: getCustomer()
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>();
		AppCursorWrapper cursorWrapper = queryCustomer(null, null);
		cursorWrapper.moveToFirst();
		try {
			while (!cursorWrapper.isAfterLast()) {
				customers.add(cursorWrapper.getCustomer());
				cursorWrapper.moveToNext();
			}
		} finally {
			cursorWrapper.close();
		}
		return customers;
	}

	public String getNameCustomerById(int id) {
		String idString = String.valueOf(id);
		AppCursorWrapper cursorWrapper = queryCustomer(TableCustomers.Cols.ID_CUSTOMER + "=?",
		                                               new String[]{idString});

		try {
			if (cursorWrapper == null) {
				return null;
			} else {
				cursorWrapper.moveToFirst();
				return cursorWrapper.getCustomerById();
			}
		} finally {
			cursorWrapper.close();
		}
	}


	//------------------------вспомогательные методы: запрос из БД, формирование ContentValues-----------------
	private AppCursorWrapper queryCustomer(String where, String[] whereArgs) {
		Cursor cursor = dateBase.query(TableCustomers.TABLE_CUSTOMERS,
		                               null,
		                               where,
		                               whereArgs,
		                               null,
		                               null,
		                               null);

		return new AppCursorWrapper(cursor);
	}

	private ContentValues getContentValues(Customer customer) {
		ContentValues contentValues = new ContentValues();
		//не надо отправлять id!
		// contentValues.put(TableCustomers.Cols.ID_CUSTOMER, customer.getIdCustomer());

		contentValues.put(TableCustomers.Cols.NAME_CUSTOMER, customer.getCustomerName());
		contentValues.put(TableCustomers.Cols.PHONE_CUSTOMER, customer.getCustomerPhone());
		return contentValues;
	}



}
