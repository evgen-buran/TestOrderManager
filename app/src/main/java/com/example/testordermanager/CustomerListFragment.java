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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testordermanager.controllers.CustomerLab;
import com.example.testordermanager.models.Customer;

import java.util.List;

public class CustomerListFragment extends Fragment {
	CustomerAdapter adapter;
	RecyclerView recyclerView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, null);
		recyclerView = view.findViewById(R.id.recycler_view);
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

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_customer_list, menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.add_customer:
				Customer customer = new Customer();
				CustomerLab.get(getActivity()).addCustomer(customer);
				Intent intent = CustomerPagerActivity.newIntent(getActivity(), customer.getIdCustomer());
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}




	private void updateUI() {
		List<Customer> customers = CustomerLab.get(getActivity()).getCustomers();
		if (adapter == null) {
			adapter = new CustomerAdapter(customers);
			recyclerView.setAdapter(adapter);
		} else {
			adapter.setCustomers(customers);
			adapter.notifyDataSetChanged();
		}
	}

	//---------------------description item of list-------------------
	private class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView tvIdCustomer;
		private TextView tvNameCustomer;
		private TextView tvPhoneCustomer;
		private Customer customer;

		public CustomerViewHolder(View itemView) {
			super(itemView);
			tvIdCustomer = itemView.findViewById(R.id.tvIdCustomer_Item);
			tvNameCustomer = itemView.findViewById(R.id.tvName_Customer_Item);
			tvPhoneCustomer = itemView.findViewById(R.id.tvPhone_Customer_Item);
			itemView.setOnClickListener(this);
		}

		public void bind(Customer customer) {
			this.customer = customer;
			tvIdCustomer.setText(String.valueOf(customer.getIdCustomer()));
			tvNameCustomer.setText(customer.getCustomerName());
			tvPhoneCustomer.setText(customer.getCustomerPhone());
		}

		@Override
		public void onClick(View v) {
			Intent intent = CustomerPagerActivity.newIntent(getActivity(), customer.getIdCustomer());
			startActivity(intent);
		}
	}



	//----------adapter--------------------------
	private class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder> {
		List<Customer> customers;

		public CustomerAdapter(List<Customer> customers) {
			this.customers = customers;
		}

		public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_customer, parent, false);
			return new CustomerViewHolder(itemView);
		}

		@Override
		public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
			holder.bind(customers.get(position));

		}

		@Override
		public int getItemCount() {
			return customers.size();
		}

		public void setCustomers(List<Customer> customers) {
			this.customers = customers;
		}
	}
}
