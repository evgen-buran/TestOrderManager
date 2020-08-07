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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testordermanager.controllers.ProductLab;
import com.example.testordermanager.models.Product;

import java.util.List;

public class ProductListFragment extends Fragment {

	RecyclerView recyclerView;
	ProductAdapter adapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
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
		inflater.inflate(R.menu.fragment_product_list, menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.add_product:
				Product product = new Product();
				ProductLab.get(getActivity()).addProduct(product);
				Intent intent = ProductPagerActivity.newIntent(getActivity(), product.getIdProduct());
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void updateUI() {
		List<Product> products = ProductLab.get(getActivity()).getProducts();
		if (adapter == null) {
			adapter = new ProductAdapter(products);
			recyclerView.setAdapter(adapter);
		} else {
			adapter.setProducts(products);
			adapter.notifyDataSetChanged();
		}
	}

	//-----------------------------RecyclerView-----------------------

	private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView tvIdProduct;
		private TextView tvNameProduct;
		private TextView tvPriceProduct;
		private Product product;

		public ProductViewHolder(View itemView) {
			super(itemView);
			tvIdProduct = itemView.findViewById(R.id.tvId_Product_List);
			tvNameProduct= itemView.findViewById(R.id.tvNameProduct_List);
			tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct_List);
			itemView.setOnClickListener(this);
		}

		public void bind(Product product) {
			this.product = product;
			tvIdProduct.setText(String.valueOf(product.getIdProduct()));
			tvNameProduct.setText(product.getNameProduct());
			tvPriceProduct.setText(String.valueOf(product.getPriceProduct()));

		}

		@Override
		public void onClick(View v) {
			Intent intent = ProductPagerActivity.newIntent(getActivity(), product.getIdProduct());
			startActivity(intent);

		}
	}

	private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
		List<Product> products;

		public ProductAdapter(List<Product> products) {
			this.products = products;
		}

		@Override
		public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_product, parent, false);

			return new ProductViewHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
			holder.bind(products.get(position));
		}

		@Override
		public int getItemCount() {
			return products.size();
		}

		public void setProducts(List<Product> products) {
			this.products = products;
		}
	}

}

