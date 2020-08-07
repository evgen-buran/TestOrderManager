package com.example.testordermanager;

import androidx.fragment.app.Fragment;

public class OrderListActivity extends SingleFragmentActivity {
	@Override
	Fragment createFragment() {
		return new OrderListFragment();
	}

}
