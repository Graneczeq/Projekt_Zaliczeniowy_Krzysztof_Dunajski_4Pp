package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.database;

import android.provider.BaseColumns;

public final class SQLiteDBContract {
    private SQLiteDBContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String CLIENTS_TABLE_NAME = "clients";
        public static final String CLIENTS_COLUMN_ID = "client_id";
        public static final String CLIENTS_COLUMN_EMAIL = "email";
        public static final String CLIENTS_COLUMN_PASSWORD = "password";
        public static final String CLIENTS_COLUMN_FULLNAME = "fullname";
        public static final String CLIENTS_COLUMN_PHONE_NUMBER = "phone_number";
        public static final String KEYBOARDS_TABLE_NAME = "keyboards";
        public static final String KEYBOARDS_COLUMN_ID = "keyboard_id";
        public static final String ITEMS_COLUMN_NAME = "name";
        public static final String ITEMS_COLUMN_DESC = "description";
        public static final String ITEMS_COLUMN_PRICE = "price";
        public static final String MICE_TABLE_NAME = "mice";
        public static final String MICE_COLUMN_ID = "mouse_id";
        public static final String MONITORS_TABLE_NAME = "monitors";
        public static final String MONITORS_COLUMN_ID = "monitor_id";
        public static final String COMPUTERS_TABLE_NAME = "computers";
        public static final String COMPUTERS_COLUMN_ID = "computer_id";
        public static final String ORDERS_TABLE_NAME = "orders";
        public static final String ORDERS_COLUMN_ID = "order_id";
        public static final String ORDERS_COLUMN_CLIENT_ID = "client_id";
        public static final String ORDERS_COLUMN_KEYBOARD_ID = "keyboard_id";
        public static final String ORDERS_COLUMN_MOUSE_ID = "mouse_id";
        public static final String ORDERS_COLUMN_MONITOR_ID = "monitor_id";
        public static final String ORDERS_COLUMN_COMPUTER_ID = "computer_id";
        public static final String ORDERS_COLUMN_ORDER_DATE = "order_date";
        public static final String ORDERS_COLUMN_ORDER_PRICE = "order_price";
    }
}
