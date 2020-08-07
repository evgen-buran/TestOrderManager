package com.example.testordermanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testordermanager.controllers.ProductLab;
import com.example.testordermanager.models.Product;

import java.util.List;

public class ProductPagerActivity extends AppCompatActivity {
	public static final String EXTRA_PRODUCT_ID = "EXTRA_PRODUCT_ID";


	ViewPager viewPager;
	List<Product> products;

	public static Intent newIntent(Context context, int id) {
		Intent intent = new Intent(context, ProductPagerActivity.class);
		intent.putExtra(EXTRA_PRODUCT_ID, id);
		return intent;
	}

	@Override
	protected void onCreate( Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pager);
		viewPager = findViewById(R.id.view_pager);
		int id = getIntent().getIntExtra(EXTRA_PRODUCT_ID, -1);
		products = ProductLab.get(this).getProducts();

		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
			@NonNull
			@Override
			public Fragment getItem(int position) {
				Product product = products.get(position);
				return ProductFragment.newInstance(product.getIdProduct());
			}

			@Override
			public int getCount() {
				return products.size();
			}
		});

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getIdProduct() == id) {
				viewPager.setCurrentItem(i);
			}
		}
	}
}
