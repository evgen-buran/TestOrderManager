package com.example.testordermanager.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testordermanager.datebase.AppCursorWrapper;
import com.example.testordermanager.datebase.SingleHelperDB;
import com.example.testordermanager.models.Order;

import java.util.ArrayList;
import java.util.List;

import static com.example.testordermanager.datebase.OrderDBScheme.*;

public class OrderLab {
	private static OrderLab orderLab;
	private Context mContext;
	private SQLiteDatabase dateBase;
	private SingleHelperDB singleHelperDB;


	private OrderLab(Context context) {
		mContext = context.getApplicationContext();
		singleHelperDB = SingleHelperDB.getInstance(mContext);
		dateBase = singleHelperDB.getOrderDBHelper().getWritableDatabase();
	}

	public static OrderLab get(Context context) {
		if (orderLab == null) {
			orderLab = new OrderLab(context);
		}
		return orderLab;
	}

	//----------основные методы: добавление, обновление, удаление, запрос одного продукта, запрос списка----------
	//add new customer in DB
	public void addOrder(Order order) {
		ContentValues contentValues = getContentValuesCustomers(order);
		long idRow = dateBase.insert(TableOrders.TABLE_ORDERS, null, contentValues);
		order.setIdOrder((int) idRow);
	}

	public void deleteOrder(Order order) {
		String id = String.valueOf(order.getIdOrder());
		dateBase.delete(TableOrders.TABLE_ORDERS,
		                TableOrders.Cols.ID_ORDER + "=?",
		                new String[]{id});

	}

	public void updateOrder(Order order) {
		String id = String.valueOf(order.getIdOrder());
		ContentValues contentValues = getContentValuesCustomers(order);
		dateBase.update(TableOrders.TABLE_ORDERS, contentValues,
		                TableOrders.Cols.ID_ORDER + "=?",
		                new String[]{id});
	}


	public Order getOrder(int id) {
		String idString = String.valueOf(id);
		AppCursorWrapper cursorWrapper = queryOrders(TableOrders.Cols.ID_ORDER + "=?",
		                                             new String[]{idString});
		try {
			if (cursorWrapper == null) {
				return null;
			} else {
				cursorWrapper.moveToFirst();
				return cursorWrapper.getOrder();
			}
		} finally {
			cursorWrapper.close();
		}
	}

	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		AppCursorWrapper cursorWrapper = queryOrders(null, null);
		try {
			if (cursorWrapper == null) {
				return null;
			} else {
				cursorWrapper.moveToFirst();
				while (!cursorWrapper.isAfterLast()) {
					orders.add(cursorWrapper.getOrder());
					cursorWrapper.moveToNext();
				}
			}
		} finally {
			cursorWrapper.close();
		}
		return orders;
	}


	//------------------------вспомогательные методы: запрос из БД, формирование ContentValues------------------
	private AppCursorWrapper queryOrders(String where, String[] whereArgs) {
		Cursor cursor = dateBase.query(TableOrders.TABLE_ORDERS,
		                               null,
		                               where,
		                               whereArgs,
		                               null,
		                               null,
		                               null);

		return new AppCursorWrapper(cursor);
	}

	private ContentValues getContentValuesCustomers(Order order) {
		ContentValues valuesBaseInfo = new ContentValues();
		valuesBaseInfo.put(TableOrders.Cols.ID_CUSTOMER, order.getIdCustomer());
		valuesBaseInfo.put(TableOrders.Cols.ID_STATUS, order.getIdStatus());
		valuesBaseInfo.put(TableOrders.Cols.DATE_START_ORDER, order.getDateStartOrder().getTime());
		
		return valuesBaseInfo;
	}

	//Запись в БД продуктов из заказа.
	private ContentValues getContentValuesProducts(int idOrder, int idProduct, int count, int priceSell) {
		ContentValues valuesProductInfo = new ContentValues();
		valuesProductInfo.put(TableOrderProduct.Cols.ID_ORDER, idOrder);
		valuesProductInfo.put(TableOrderProduct.Cols.ID_PRODUCT, idProduct);
		valuesProductInfo.put(TableOrderProduct.Cols.COUNT_PRODUCT, count);
		valuesProductInfo.put(TableOrderProduct.Cols.PRICE_SELL, priceSell);
		return valuesProductInfo;
	}

}
