package com.example.testordermanager.datebase;

import android.content.Context;

public class SingleHelperDB {
	private static SingleHelperDB singleHelperDB;
	private static OrderDBHelper orderDBHelper;

	private SingleHelperDB(Context context) {
		orderDBHelper = new OrderDBHelper(context);
	}



	public static SingleHelperDB getInstance(Context context) {
		if (singleHelperDB == null) {
			singleHelperDB = new SingleHelperDB(context);
		}
		return singleHelperDB;
	}

	public  OrderDBHelper getOrderDBHelper() {
		return orderDBHelper;
	}
}
