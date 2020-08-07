package com.example.testordermanager.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testordermanager.datebase.OrderDBScheme.TableCustomers;
import com.example.testordermanager.datebase.OrderDBScheme.TableOrderProduct;
import com.example.testordermanager.datebase.OrderDBScheme.TableProducts;
import com.example.testordermanager.datebase.OrderDBScheme.TableStatus;
import com.example.testordermanager.datebase.OrderDBScheme.TableOrders;

public class OrderDBHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	private static final String DATABASE_NAME = "orderBase.db";

	public OrderDBHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TableCustomers.TABLE_CUSTOMERS + "(" +
			           TableCustomers.Cols.ID_CUSTOMER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			           TableCustomers.Cols.NAME_CUSTOMER + " TEXT," +
			           TableCustomers.Cols.PHONE_CUSTOMER + " TEXT" + ");");

		db.execSQL("CREATE TABLE " + TableProducts.TABLE_PRODUCTS + "(" +
			           TableProducts.Cols.ID_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			           TableProducts.Cols.NAME_PRODUCT + " TEXT," +
			           TableProducts.Cols.PRICE_PRODUCT + " INTEGER" + ");");

		db.execSQL("CREATE TABLE " + TableStatus.TABLE_STATUS + "(" +
			           TableStatus.Cols.ID_STATUS + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			           TableStatus.Cols.NAME_STATUS + " TEXT"  +" UNIQUE"+");");

		db.execSQL("CREATE TABLE " + TableOrders.TABLE_ORDERS + "(" +
			           TableOrders.Cols.ID_ORDER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			           TableOrders.Cols.ID_CUSTOMER + " INTEGER," +
			           TableOrders.Cols.DATE_START_ORDER + " TEXT," +
			           TableOrders.Cols.DATE_FINISH_ORDER + " TEXT," +
			           TableOrders.Cols.ID_STATUS + " INTEGER," +
			           " FOREIGN KEY " + "(" + TableOrders.Cols.ID_CUSTOMER + ")" + " REFERENCES " + TableCustomers.TABLE_CUSTOMERS
			           + "(" + TableCustomers.Cols.ID_CUSTOMER + ")" + " ON UPDATE CASCADE," +
			           " FOREIGN KEY " + "(" + TableOrders.Cols.ID_STATUS + ")" + " REFERENCES " +
			           TableStatus.TABLE_STATUS + "(" + TableStatus.Cols.ID_STATUS +
			           ")" + " ON UPDATE CASCADE " + " ON DELETE CASCADE);");

		db.execSQL("CREATE TABLE " + TableOrderProduct.TABLE_ORDER_PRODUCTS + "(" +
			           TableOrderProduct.Cols.ID_ORDER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			           TableOrderProduct.Cols.ID_PRODUCT + " INTEGER," +
			           TableOrderProduct.Cols.COUNT_PRODUCT + " INTEGER," +
			           TableOrderProduct.Cols.PRICE_SELL + " INTEGER," +
			           " FOREIGN KEY " + "(" + TableOrderProduct.Cols.ID_ORDER + ")" +
			           " REFERENCES " + TableOrders.TABLE_ORDERS + "(" + TableOrders.Cols.ID_ORDER + ")" +
			           " ON UPDATE CASCADE " + " ON DELETE CASCADE," +
			           " FOREIGN KEY " + "(" + TableOrderProduct.Cols.ID_PRODUCT + ")" +
			           " REFERENCES " + TableProducts.TABLE_PRODUCTS + "(" + TableProducts.Cols.ID_PRODUCT + ")" +
			           " ON UPDATE CASCADE " + " ON DELETE CASCADE" + ");");


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
