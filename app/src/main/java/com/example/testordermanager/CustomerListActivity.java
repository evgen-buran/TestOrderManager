package com.example.testordermanager;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class CustomerListActivity extends SingleFragmentActivity {

	public static Intent newIntent(Context context) {
		Intent intent = new Intent(context, CustomerListActivity.class);
		return intent;
	}

	@Override
	protected Fragment createFragment() {
		return new CustomerListFragment();
	}

}
