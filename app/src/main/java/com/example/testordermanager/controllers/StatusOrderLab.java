package com.example.testordermanager.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testordermanager.datebase.AppCursorWrapper;
import com.example.testordermanager.datebase.SingleHelperDB;
import com.example.testordermanager.models.StatusOrder;

import java.util.ArrayList;
import java.util.List;

import static com.example.testordermanager.datebase.OrderDBScheme.*;

public class StatusOrderLab {
	public static StatusOrderLab statusOrderLab;
	private Context context;
	private SQLiteDatabase dateBase;
	private SingleHelperDB singleHelperDB;

	private StatusOrderLab(Context context) {
		this.context = context.getApplicationContext();
		singleHelperDB = SingleHelperDB.getInstance(this.context);
		dateBase = singleHelperDB.getOrderDBHelper().getWritableDatabase();
	}

	public static StatusOrderLab get(Context context) {
		if (statusOrderLab == null) {
			statusOrderLab = new StatusOrderLab(context);
		}
		return statusOrderLab;
	}

	//-------------------------------------------------------
	public void addStatus(StatusOrder status) {
		ContentValues values = getContentValues(status);
		long idStatus = dateBase.insert(TableStatus.TABLE_STATUS, null, values);
		status.setIdStatus((int) idStatus);
	}

	public void deleteStatus(StatusOrder statusOrder) {
		String idString = String.valueOf(statusOrder.getIdStatus());
		dateBase.delete(TableStatus.TABLE_STATUS,
		                TableStatus.Cols.ID_STATUS + "=?",
		                new String[]{idString});

	}

	public void updateStatus(StatusOrder statusOrder) {
		ContentValues values = getContentValues(statusOrder);
		String idString = String.valueOf(statusOrder.getIdStatus());
		dateBase.update(TableStatus.TABLE_STATUS, values,
		                TableStatus.Cols.ID_STATUS + "= ?",
		                new String[]{idString});
	}

	//-----------------------------
	public StatusOrder getStatus(int id) {
		String idString = String.valueOf(id);
		AppCursorWrapper cursorWrapper = queryStatus(TableStatus.Cols.ID_STATUS + "=?",
		                                             new String[]{idString});
		try {
			if (cursorWrapper == null) {
				return null;
			} else {
				cursorWrapper.moveToFirst();
				return cursorWrapper.getStatusOrder();
			}
		} finally {
			cursorWrapper.close();
		}
	}

	public String getNameStatusById(int id) {
		String idString = String.valueOf(id);
		AppCursorWrapper cursorWrapper = queryStatus(TableStatus.Cols.ID_STATUS + "=?",
		                                             new String[]{idString});
		try {
			if (cursorWrapper == null) {
				return null;
			} else {
				cursorWrapper.moveToFirst();
				return cursorWrapper.getStatusById();
			}
		} finally {
			cursorWrapper.close();
		}
	}

	public List<StatusOrder> getStatusOrders() {
		List<StatusOrder> statusOrders = new ArrayList<>();
		AppCursorWrapper cursorWrapper = queryStatus(null, null);
		cursorWrapper.moveToFirst();
		try {
			while (!cursorWrapper.isAfterLast()) {
				statusOrders.add(cursorWrapper.getStatusOrder());
				cursorWrapper.moveToNext();
			}
		} finally {
			cursorWrapper.close();
		}
		return statusOrders;
	}

	//----------------------------------
	public AppCursorWrapper queryStatus(String where, String[] whereArgs) {
		Cursor cursor = dateBase.query(TableStatus.TABLE_STATUS,
		                               null,
		                               where,
		                               whereArgs,
		                               null,
		                               null,
		                               null);
		return new AppCursorWrapper(cursor);
	}

	public ContentValues getContentValues(StatusOrder status) {
		ContentValues values = new ContentValues();
		values.put(TableStatus.Cols.NAME_STATUS, status.getNameStatus());
		return values;
	}


}
