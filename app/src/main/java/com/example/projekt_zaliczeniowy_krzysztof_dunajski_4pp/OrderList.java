package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.database.SQLiteDBContract;
import com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.database.SQLiteDBHelper;
import com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.other.Adapter;

import java.util.ArrayList;
import java.util.Objects;

public class OrderList extends AppCompatActivity {

    Utils utils;
    ArrayList<String> keyboardNames, mouseNames, monitorNames, computerNames;
    ArrayList<String> keyboardsNames, miceNames, monitorsNames, computersNames;
    ArrayList<Float> keyboardPrices, mousePrices, monitorPrices, computerPrices;
    ArrayList<Float> keyboardsPrices, micePrices, monitorsPrices, computersPrices;
    ArrayList<Float> orderPrices;
    ArrayList<String> orderDates;
    ArrayList<Integer> orderIds;
    Cursor cursor, keyboardNamesCursor, keyboardPricesCursor, mouseNamesCursor, mousePricesCursor, monitorNamesCursor, monitorPricesCursor, computerNamesCursor, computerPricesCursor;
    SQLiteDBHelper dbHelper;
    RecyclerView recyclerView;
    int userId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.logout:
                utils.logout();
                utils.openLoginActivity();
                dbHelper.truncate();
                finish();
                break;
            case R.id.author:
                utils.openAuthorActivity();
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        utils = new Utils(this);

        recyclerView = findViewById(R.id.allOrdersRecyclerView);

        dbHelper = new SQLiteDBHelper(this);

        keyboardNames = new ArrayList<>();
        mouseNames = new ArrayList<>();
        monitorNames = new ArrayList<>();
        computerNames = new ArrayList<>();
        keyboardPrices = new ArrayList<>();
        mousePrices = new ArrayList<>();
        monitorPrices = new ArrayList<>();
        computerPrices = new ArrayList<>();
        orderPrices = new ArrayList<>();
        orderDates = new ArrayList<>();
        orderIds = new ArrayList<>();

        userId = utils.getUserId();

        cursor = dbHelper.query(userId);

        insertItemNames();
        insertItemPrices();

        while(cursor.moveToNext()) {
            long orderId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_ID));
            orderIds.add((int) orderId);
            if(cursor.getType(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_KEYBOARD_ID)) == Cursor.FIELD_TYPE_NULL) {
                keyboardNames.add("");
                keyboardPrices.add((float) 0);
            } else {
                keyboardNames.add(keyboardsNames.get(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_KEYBOARD_ID))));
                float keyboardPrice = keyboardsPrices.get(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_KEYBOARD_ID)));
                keyboardPrices.add(keyboardPrice);
            }
            if(cursor.getType(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_MOUSE_ID)) == Cursor.FIELD_TYPE_NULL) {
                mouseNames.add("");
                mousePrices.add((float) 0);
            } else {
                mouseNames.add(miceNames.get(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_MOUSE_ID))));
                float mousePrice = micePrices.get(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_MOUSE_ID)));
                mousePrices.add(mousePrice);
            }
            if(cursor.getType(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_MONITOR_ID)) == Cursor.FIELD_TYPE_NULL) {
                monitorNames.add("");
                monitorPrices.add((float) 0);
            } else {
                monitorNames.add(monitorsNames.get(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_MONITOR_ID))));
                float monitorPrice = monitorsPrices.get(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_MONITOR_ID)));
                monitorPrices.add(monitorPrice);
            }
            if(cursor.getType(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_COMPUTER_ID)) == Cursor.FIELD_TYPE_NULL) {
                computerNames.add("");
                computerPrices.add((float) 0);
            } else {
                computerNames.add(computersNames.get(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_COMPUTER_ID))));
                float computerPrice = computersPrices.get(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_COMPUTER_ID)));
                computerPrices.add(computerPrice);
            }
            Float orderPrice = cursor.getFloat(
                    cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_ORDER_PRICE)
            );
            String orderDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ORDERS_COLUMN_ORDER_DATE));
            orderDates.add(orderDate);
            orderPrices.add(orderPrice);
        }
        cursor.close();
        Adapter adapter = new Adapter(keyboardNames, mouseNames, monitorNames, computerNames, keyboardPrices, mousePrices, monitorPrices, computerPrices, orderPrices, orderDates, orderIds);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.divider)));
        recyclerView.addItemDecoration(divider);
    }

    public void insertItemNames() {
        keyboardsNames = new ArrayList<>();
        miceNames = new ArrayList<>();
        monitorsNames = new ArrayList<>();
        computersNames = new ArrayList<>();
        keyboardNamesCursor = dbHelper.getNames("keyboards");
        mouseNamesCursor = dbHelper.getNames("mice");
        monitorNamesCursor = dbHelper.getNames("monitors");
        computerNamesCursor = dbHelper.getNames("computers");

        while(keyboardNamesCursor.moveToNext()) {
            String keyboardName = keyboardNamesCursor.getString(
                    keyboardNamesCursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ITEMS_COLUMN_NAME)
            );
            keyboardsNames.add(keyboardName);
        }
        while(mouseNamesCursor.moveToNext()) {
            String mouseName = mouseNamesCursor.getString(
                    mouseNamesCursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ITEMS_COLUMN_NAME)
            );
            miceNames.add(mouseName);
        }
        while(monitorNamesCursor.moveToNext()) {
            String monitorName = monitorNamesCursor.getString(
                    monitorNamesCursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ITEMS_COLUMN_NAME)
            );
            monitorsNames.add(monitorName);
        }
        while(computerNamesCursor.moveToNext()) {
            String computerName = computerNamesCursor.getString(
                    computerNamesCursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ITEMS_COLUMN_NAME)
            );
            computersNames.add(computerName);
        }
    }

    public void insertItemPrices() {
        keyboardsPrices = new ArrayList<>();
        micePrices = new ArrayList<>();
        monitorsPrices = new ArrayList<>();
        computersPrices = new ArrayList<>();
        keyboardPricesCursor = dbHelper.getPrices("keyboards");
        mousePricesCursor = dbHelper.getPrices("mice");
        monitorPricesCursor = dbHelper.getPrices("monitors");
        computerPricesCursor = dbHelper.getPrices("computers");

        while(keyboardPricesCursor.moveToNext()) {
            Float keyboardPrice = keyboardPricesCursor.getFloat(
                    keyboardPricesCursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ITEMS_COLUMN_PRICE)
            );
            keyboardsPrices.add(keyboardPrice);
        }
        while(mousePricesCursor.moveToNext()) {
            Float mousePrice = mousePricesCursor.getFloat(
                    mousePricesCursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ITEMS_COLUMN_PRICE)
            );
            micePrices.add(mousePrice);
        }
        while(monitorPricesCursor.moveToNext()) {
            Float monitorPrice = monitorPricesCursor.getFloat(
                    monitorPricesCursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ITEMS_COLUMN_PRICE)
            );
            monitorsPrices.add(monitorPrice);
        }
        while(computerPricesCursor.moveToNext()) {
            Float computerPrice = computerPricesCursor.getFloat(
                    computerPricesCursor.getColumnIndexOrThrow(SQLiteDBContract.FeedEntry.ITEMS_COLUMN_PRICE)
            );
            computersPrices.add(computerPrice);
        }
    }
}
