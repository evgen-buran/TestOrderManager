package com.example.testordermanager.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.testordermanager.datebase.AppCursorWrapper;
import com.example.testordermanager.datebase.OrderDBScheme.TableProducts;
import com.example.testordermanager.datebase.SingleHelperDB;
import com.example.testordermanager.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductLab {
	private static ProductLab productLab;
	private Context mContext;
	private SQLiteDatabase dateBase;
	private SingleHelperDB singleHelperDB;


	private ProductLab(Context context) {
		mContext = context.getApplicationContext();
		singleHelperDB = SingleHelperDB.getInstance(mContext);
		dateBase = singleHelperDB.getOrderDBHelper().getWritableDatabase();
	}

	public static ProductLab get(Context context) {
		if (productLab == null) {
			productLab = new ProductLab(context);
		}
		return productLab;
	}

	//----------основные методы: добавление, обновление, удаление, запрос одного продукта, запрос списка----------
	//add new customer in DB
	public void addProduct(Product product) {
		ContentValues contentValues = getContentValues(product);
		long idRow = dateBase.insert(TableProducts.TABLE_PRODUCTS, null, contentValues);
		product.setIdProduct((int) idRow);
	}

	public void deleteProduct(Product product) {
		String id = String.valueOf(product.getIdProduct());
		dateBase.delete(TableProducts.TABLE_PRODUCTS,
		                TableProducts.Cols.ID_PRODUCT + "=?",
		                new String[]{id});

	}

	public void updateProduct(Product product) {
		String id = String.valueOf(product.getIdProduct());
		ContentValues contentValues = getContentValues(product);
		dateBase.update(TableProducts.TABLE_PRODUCTS, contentValues,
		                TableProducts.Cols.ID_PRODUCT + "=?",
		                new String[]{id});
	}


	public Product getProduct(int id) {
		String idString = String.valueOf(id);
		AppCursorWrapper cursorWrapper = queryProducts(TableProducts.Cols.ID_PRODUCT + "=?",
		                                               new String[]{idString});
		try {
			if (cursorWrapper == null) {
				return null;
			} else {
				cursorWrapper.moveToFirst();
				return cursorWrapper.getProduct();
			}
		} finally {
			cursorWrapper.close();
		}
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		AppCursorWrapper cursorWrapper = queryProducts(null, null);
		try {
			if (cursorWrapper == null) {
				return null;
			} else {
				cursorWrapper.moveToFirst();
				while (!cursorWrapper.isAfterLast()) {
					products.add(cursorWrapper.getProduct());
					cursorWrapper.moveToNext();
				}
			}
		} finally {
			cursorWrapper.close();
		}
		return products;
	}


	//------------------------вспомогательные методы: запрос из БД, формирование ContentValues------------------
	private AppCursorWrapper queryProducts(String where, String[] whereArgs) {
		Cursor cursor = dateBase.query(TableProducts.TABLE_PRODUCTS,
		                               null,
		                               where,
		                               whereArgs,
		                               null,
		                               null,
		                               null);

		return new AppCursorWrapper(cursor);
	}

	private ContentValues getContentValues(Product product) {
		ContentValues values = new ContentValues();
		values.put(TableProducts.Cols.NAME_PRODUCT, product.getNameProduct());
		values.put(TableProducts.Cols.PRICE_PRODUCT, product.getPriceProduct());
		return values;
	}
}
