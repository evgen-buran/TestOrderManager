package com.example.testordermanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testordermanager.controllers.OrderLab;
import com.example.testordermanager.models.Order;

import java.util.List;

public class OrderPagerActivity extends AppCompatActivity {
	public static final String EXTRA_ORDER_ID = "EXTRA_ORDER_ID";
	ViewPager viewPager;
	List<Order> orders;

	public static Intent newIntent(Context context, int id) {
		Intent intent = new Intent(context, OrderPagerActivity.class);
		intent.putExtra(EXTRA_ORDER_ID, id);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pager);
		viewPager = findViewById(R.id.view_pager);
		int id = getIntent().getIntExtra(EXTRA_ORDER_ID, -1);
		orders = OrderLab.get(this).getOrders();

		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
			@NonNull
			@Override
			public Fragment getItem(int position) {
				Order order = orders.get(position);
				return OrderFragment.newInstance(order.getIdOrder());
			}

			@Override
			public int getCount() {
				return orders.size();
			}
		});

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getIdOrder() == id) {
				viewPager.setCurrentItem(i);
				break;
			}
		}
	}
}

