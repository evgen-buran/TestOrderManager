package com.example.testordermanager;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class StatusOrderListActivity extends SingleFragmentActivity {
	public static Intent newIntent(Context context) {
		Intent intent = new Intent(context, StatusOrderListActivity.class);
		return intent;
	}

	@Override
	Fragment createFragment() {
		return new StatusOrderListFragment();
	}
}
