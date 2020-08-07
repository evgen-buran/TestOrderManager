package com.example.testordermanager;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ProductListActivity extends SingleFragmentActivity {

	public static Intent newIntent(Context context) {
		Intent intent = new Intent(context, ProductListActivity.class);
		return intent;
	}

	@Override
	protected Fragment createFragment() {
		return new ProductListFragment();
	}

}
