package com.example.testordermanager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testordermanager.controllers.CustomerLab;
import com.example.testordermanager.controllers.ProductLab;
import com.example.testordermanager.models.Product;

public class ProductFragment extends Fragment {
	private static final String ARG_ID_PRODUCT = "arg_id_product";

	TextView tvIdProduct;
	EditText etNameProduct;
	EditText etPriceProduct;
	Product product;

	public static Fragment newInstance(int id) {
		Bundle arg = new Bundle();
		arg.putInt(ARG_ID_PRODUCT, id);
		Fragment fragment = new ProductFragment();
		fragment.setArguments(arg);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int id = getArguments().getInt(ARG_ID_PRODUCT);
		product = ProductLab.get(getActivity()).getProduct(id);
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_product, container, false);
		tvIdProduct = view.findViewById(R.id.tv_id_product_fragment);
		etNameProduct = view.findViewById(R.id.et_name_product_fragment);
		etPriceProduct = view.findViewById(R.id.et_price_product_fragment);

		tvIdProduct.setText(String.valueOf(product.getIdProduct()));
		etNameProduct.setText(product.getNameProduct());
		etPriceProduct.setText(String.valueOf(product.getPriceProduct()));

		etNameProduct.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				product.setNameProduct(s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		etPriceProduct.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				int price = Integer.parseInt(String.valueOf(s));
				product.setPriceProduct(price);
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		return view;
	}

	@Override
	public void onPause() {
		super.onPause();
		ProductLab.get(getActivity()).updateProduct(product);
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_product, menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.del_product:
				ProductLab.get(getActivity()).deleteProduct(product);
				getActivity().finish();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}

	}

}
