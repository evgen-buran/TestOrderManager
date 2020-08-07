package com.example.testordermanager.datebase;

public class OrderDBScheme {
	//table of customers
	public static final class TableCustomers {
		public static final String TABLE_CUSTOMERS = "customers";

		public static final class Cols {
			public static final String ID_CUSTOMER = "id_customer";
			public static final String NAME_CUSTOMER = "name_customer";
			public static final String PHONE_CUSTOMER = "phone_customer";
		}
	}

	//table of products
	public static final class TableProducts {
		public static final String TABLE_PRODUCTS = "products";

		public static final class Cols {
			public static final String ID_PRODUCT = "id_product";
			public static final String NAME_PRODUCT = "name_product";
			public static final String PRICE_PRODUCT = "price_product";
		}
	}

	//table of orders
	public static final class TableOrders {
		public static final String TABLE_ORDERS = "orders";

		public static final class Cols {
			public static final String ID_ORDER = "id_order";
			public static final String ID_CUSTOMER = "id_customer";
			public static final String DATE_START_ORDER = "date_start_order";
			public static final String DATE_FINISH_ORDER = "date_finish_order";
			public static final String ID_STATUS = "status_order";
		}
	}

	//table of order&product
	public static final class TableOrderProduct {
		public static final String TABLE_ORDER_PRODUCTS = "order_products";

		public static final class Cols {
			public static final String ID_ORDER = "id_order";
			public static final String ID_PRODUCT = "id_product";
			public static final String COUNT_PRODUCT = "count_product";
			public static final String PRICE_SELL = "price_sell";
		}
	}

	//table of status
	public static final class TableStatus {
		public static final String TABLE_STATUS = "status";

		public static final class Cols {
			public static final String ID_STATUS = "id_status";
			public static final String NAME_STATUS = "name_status";
		}
	}
}
