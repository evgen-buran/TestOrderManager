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
import androidx.fragment.app.FragmentManager;

import com.example.testordermanager.controllers.StatusOrderLab;
import com.example.testordermanager.models.StatusOrder;

public class StatusOrderFragment extends Fragment {
	private static final String ARG_ID_STATUS = "arg_id_status";
	TextView tvIdStatus;
	EditText etNameStatus;
	StatusOrder statusOrder;

	public Fragment newInstance(int id) {
		Bundle arg = new Bundle();
		arg.putInt(ARG_ID_STATUS, id);
		Fragment fragment = new StatusOrderFragment();
		fragment.setArguments(arg);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int id = getArguments().getInt(ARG_ID_STATUS);
		statusOrder = StatusOrderLab.get(getActivity()).getStatus(id);
		setHasOptionsMenu(true);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_status_order, container, false);
		tvIdStatus = view.findViewById(R.id.tvId_Status_fragment);
		etNameStatus = view.findViewById(R.id.etNameStatus_Fragment);
		tvIdStatus.setText(String.valueOf(statusOrder.getIdStatus()));
		etNameStatus.setText(statusOrder.getNameStatus());

		etNameStatus.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				statusOrder.setNameStatus(s.toString());
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
		StatusOrderLab.get(getActivity()).updateStatus(statusOrder);
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_status, menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.del_status:
				StatusOrderLab.get(getActivity()).deleteStatus(statusOrder);
				getActivity().finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}

	}

}
