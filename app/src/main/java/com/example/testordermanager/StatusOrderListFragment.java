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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testordermanager.controllers.StatusOrderLab;
import com.example.testordermanager.models.StatusOrder;

import java.util.List;

public class StatusOrderListFragment extends Fragment {
	StatusOrderAdapter adapter;
	RecyclerView recyclerView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);
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
		inflater.inflate(R.menu.fragment_status_list, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.add_status:
				StatusOrder statusOrder = new StatusOrder();
				StatusOrderLab.get(getActivity()).addStatus(statusOrder);
				Intent intent = StatusOrderPagerActivity.newIntent(getActivity(), statusOrder.getIdStatus());
				Toast.makeText(getActivity(), "клик",Toast.LENGTH_SHORT).show();
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}

	private void updateUI() {
		List<StatusOrder> statusOrders = StatusOrderLab.get(getActivity()).getStatusOrders();
		if (adapter == null) {
			adapter = new StatusOrderAdapter(statusOrders);
			recyclerView.setAdapter(adapter);
		} else {
			adapter.setStatusOrders(statusOrders);
			adapter.notifyDataSetChanged();
		}

	}

	private class StatusViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView tvIdStatus;
		TextView tvNameStatusOrder;
		private StatusOrder statusOrder;

		public StatusViewHolder(View itemView) {
			super(itemView);
			tvIdStatus = itemView.findViewById(R.id.idStatusOrder_List_Fragment);
			tvNameStatusOrder = itemView.findViewById(R.id.tvNametvStatusOrder_List_Fragment);
			itemView.setOnClickListener(this);
		}

		public void bind(StatusOrder statusOrder) {
			this.statusOrder = statusOrder;
			tvIdStatus.setText(String.valueOf(statusOrder.getIdStatus()));
			tvNameStatusOrder.setText(statusOrder.getNameStatus());

		}

		@Override
		public void onClick(View v) {
			Intent intent = StatusOrderPagerActivity.newIntent(getActivity(), statusOrder.getIdStatus());
			startActivity(intent);

		}
	}

	private class StatusOrderAdapter extends RecyclerView.Adapter<StatusViewHolder> {
		List<StatusOrder> statusOrders;

		public StatusOrderAdapter(List<StatusOrder> statusOrders) {
			this.statusOrders = statusOrders;
		}

		@Override
		public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_status_order, parent, false);
			return new StatusViewHolder(itemView);
		}

		@Override
		public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
			holder.bind(statusOrders.get(position));
		}

		@Override
		public int getItemCount() {
			return statusOrders.size();
		}

		public void setStatusOrders(List<StatusOrder> statusOrders) {
			this.statusOrders = statusOrders;
		}
	}
}
