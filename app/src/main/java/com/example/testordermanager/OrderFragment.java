package com.example.testordermanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testordermanager.controllers.OrderLab;
import com.example.testordermanager.models.Order;

public class OrderFragment extends Fragment {
	private static final String ARG_ID_ORDER = "arg_id_order";
	private TextView tvIdOrder;
	private TextView tvIdCustomer;
	private TextView tvIdStatus;
	private Order order;

	public static Fragment newInstance(int id) {
		Bundle arg = new Bundle();
		arg.putInt(ARG_ID_ORDER, id);
		OrderFragment fragment = new OrderFragment();
		fragment.setArguments(arg);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int id = getArguments().getInt(ARG_ID_ORDER);
		order = OrderLab.get(getActivity()).getOrder(id);
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_order_1, container, false);
		tvIdOrder = view.findViewById(R.id.tvIdOrder_OrderFragment);
		tvIdCustomer = view.findViewById(R.id.tvIdCustomer_OrderFragment);
		tvIdStatus = view.findViewById(R.id.tvStatus_OrderFragment);

		tvIdOrder.setText(String.valueOf(order.getIdOrder()));
		tvIdCustomer.setText(String.valueOf(order.getIdCustomer()));
		tvIdStatus.setText(String.valueOf(order.getIdStatus()));
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_order, menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.delete_order:
				OrderLab.get(getActivity()).deleteOrder(order);
				getActivity().finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}
}
