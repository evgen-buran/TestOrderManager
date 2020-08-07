package com.example.testordermanager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testordermanager.controllers.CustomerLab;
import com.example.testordermanager.models.Customer;

public class CustomerFragment extends Fragment {
	private static final String ARG_ID_CUSTOMER = "arg_id_customer";
	TextView tvIdCustomer;
	EditText etNameCustomer;
	EditText etPhoneCustomer;
	Customer customer;

	public static Fragment newInstance(int id) {
		Bundle arg = new Bundle();
		arg.putInt(ARG_ID_CUSTOMER, id);
		Fragment fragment = new CustomerFragment();
		fragment.setArguments(arg);
		return fragment;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//данные берутся из интента активности
		//int id = getActivity().getIntent().getIntExtra(CustomerActivity.EXTRA_CUSTOMER_ID, -1);
		int id = getArguments().getInt(ARG_ID_CUSTOMER);
		customer = CustomerLab.get(getActivity()).getCustomer(id);
		Log.d("my_log", "listCustomers");
	}


	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_customer, container, false);
		tvIdCustomer = view.findViewById(R.id.tvId_Fragment_Customer);
		etNameCustomer = view.findViewById(R.id.etName_Fragment_Customer);
		etPhoneCustomer = view.findViewById(R.id.etPhone_Fragment_Customer);
		setHasOptionsMenu(true);


		etNameCustomer.setText(customer.getCustomerName());
		String idString = String.valueOf(customer.getIdCustomer());
		tvIdCustomer.setText(idString);
		etNameCustomer.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				customer.setCustomerName(s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		etPhoneCustomer.setText(customer.getCustomerPhone());
		etPhoneCustomer.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				customer.setCustomerPhone(s.toString());
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
		CustomerLab.get(getActivity()).updateCustomer(customer);
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_customer, menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.del_customer:
				CustomerLab.get(getActivity()).deleteCustomer(customer);
				getActivity().finish();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}

	}
}
