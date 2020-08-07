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

import com.example.testordermanager.controllers.StatusOrderLab;
import com.example.testordermanager.models.StatusOrder;

import java.util.List;

public class StatusOrderPagerActivity extends AppCompatActivity {
	public static final String EXTRA_STATUS_ID = "EXTRA_STATUS_ID";
	ViewPager viewPager;
	List<StatusOrder> statusOrders;

	public static Intent newIntent(Context context, int id) {
		Intent intent = new Intent(context, StatusOrderPagerActivity.class);
		intent.putExtra(EXTRA_STATUS_ID, id);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pager);
		viewPager = findViewById(R.id.view_pager);
		int id = getIntent().getIntExtra(EXTRA_STATUS_ID, -1);
		statusOrders = StatusOrderLab.get(this).getStatusOrders();

		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
			@NonNull
			@Override
			public Fragment getItem(int position) {
				StatusOrder statusOrder = statusOrders.get(position);
				int id = statusOrder.getIdStatus();
				return new StatusOrderFragment().newInstance(id);
			}

			@Override
			public int getCount() {
				return statusOrders.size();
			}
		});
		for (int i = 0; i < statusOrders.size(); i++) {
			if (statusOrders.get(i).getIdStatus() == id) {
				viewPager.setCurrentItem(i);
				break;
			}
		}



	}
}
