package com.example.testordermanager;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

//активити уже не нужна, оставлена для примера.
//используется, если не применяю вьюПейджер и в данном варианте передача id идет через интент (принадлежит данному
// активити и CustomerFragment поэтому не может рабоатаь без данной активити.).
// в новом варианте через аргументы фрагмента, теперь CustomerFragment даже не знает о существовании родительского активити.
//просто хранит в своем же хранилище нужные данные и берет их оттуда.

public class CustomerActivity_dontUse extends SingleFragmentActivity {
	public static final String EXTRA_CUSTOMER_ID = "EXTRA_CUSTOMER_ID";

	@Override
	protected Fragment createFragment() {
		Fragment fragment = new CustomerFragment();
		return fragment;
	}

	public static Intent newIntent(Context context, int id) {
		Intent intent = new Intent(context, CustomerActivity_dontUse.class);
		intent.putExtra(EXTRA_CUSTOMER_ID, id);
		return intent;
	}


}
