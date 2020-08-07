package com.example.testordermanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testordermanager.controllers.CustomerLab;
import com.example.testordermanager.controllers.OrderLab;
import com.example.testordermanager.controllers.StatusOrderLab;
import com.example.testordermanager.models.Order;

import java.util.List;

public class OrderListFragment extends Fragment {
	RecyclerView recyclerView;
	OrderAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_order_list, container, false);
		recyclerView = view.findViewById(R.id.order_recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		setHasOptionsMenu(true);

		updateUI();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		updateUI();
	}

	private void updateUI() {
		List<Order> orders = OrderLab.get(getActivity()).getOrders();
		if (adapter == null) {
			adapter = new OrderAdapter(orders);
			recyclerView.setAdapter(adapter);
		} else {
			adapter.setOrders(orders);
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_order_list, menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.add_order:
				Order order = new Order();
				OrderLab.get(getActivity()).addOrder(order);
				Intent intent = OrderPagerActivity.newIntent(getActivity(), order.getIdOrder());
				startActivity(intent);
				return true;

			case R.id.view_status:
				Intent intentStatus = StatusOrderListActivity.newIntent(getActivity());
				startActivity(intentStatus);
				return true;

			case R.id.view_product:
				Intent intentProducts = ProductListActivity.newIntent(getActivity());
				startActivity(intentProducts);
				return true;

			case R.id.view_customers:
				Intent intentCustomers = CustomerListActivity.newIntent(getActivity());
				startActivity(intentCustomers);
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}

	}

	private class OrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private Order order;
		private TextView tvIdOrder;
		private TextView tvIdCustomer;
		private TextView tvIdStatus;


		public OrderHolder(@NonNull View itemView) {
			super(itemView);
			tvIdOrder = itemView.findViewById(R.id.tvIdOrder_OrderList);
			tvIdCustomer = itemView.findViewById(R.id.tvIdCustomer_OrderList);
			tvIdStatus = itemView.findViewById(R.id.tvIdStatus_OrderList);
			itemView.setOnClickListener(this);
		}

		public void bind(Order order) {
			this.order = order;
			String nameCustomer = CustomerLab.get(getActivity()).getNameCustomerById(order.getIdCustomer());
			String nameStatus = StatusOrderLab.get(getActivity()).getNameStatusById(order.getIdStatus());
			tvIdOrder.setText(String.valueOf(order.getIdOrder()));
			tvIdCustomer.setText(nameCustomer);
			tvIdStatus.setText(nameStatus);
		}

		@Override
		public void onClick(View v) {
			Intent intent = OrderPagerActivity.newIntent(getActivity(), order.getIdOrder());
			startActivity(intent);
		}
	}

	//---------------adapter--------------------------
	private class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {
		List<Order> orders;

		public OrderAdapter(List<Order> orders) {
			this.orders = orders;
		}

		@Override
		public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_order_1, parent, false);
			return new OrderHolder(view);
		}

		@Override
		public void onBindViewHolder(OrderHolder holder, int position) {
			holder.bind(orders.get(position));
		}

		@Override
		public int getItemCount() {
			return orders.size();
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
	}
}
