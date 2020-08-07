package com.example.testordermanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testordermanager.controllers.CustomerLab;
import com.example.testordermanager.models.Customer;

import java.util.List;

public class CustomerPagerActivity extends AppCompatActivity {
	public static final String EXTRA_CUSTOMER_ID = "EXTRA_CUSTOMER_ID";
	ViewPager viewPager;
	List<Customer> customers;

	public static Intent newIntent(Context context, int id) {
		Intent intent = new Intent(context, CustomerPagerActivity.class);
		intent.putExtra(EXTRA_CUSTOMER_ID, id);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pager);
		viewPager = findViewById(R.id.view_pager);
		int id = getIntent().getIntExtra(EXTRA_CUSTOMER_ID, -1);
		customers = CustomerLab.get(this).getCustomers();

		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
			@NonNull
			@Override
			public Fragment getItem(int position) {
				Customer customer = customers.get(position);
				return CustomerFragment.newInstance(customer.getIdCustomer());
			}

			@Override
			public int getCount() {
				return customers.size();
			}
		});

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getIdCustomer() == id) {
				viewPager.setCurrentItem(i);
				break;
			}
		}
	}
}

