package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.database.SQLiteDBHelper;

public class AuthorInfo extends AppCompatActivity {

    Utils utils;
    SQLiteDBHelper dbHelper;

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
            case R.id.allOrders:
                utils.openAllOrdersActivity();
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
        setContentView(R.layout.activity_author_name);

        utils = new Utils(this);
        dbHelper = new SQLiteDBHelper(this);
    }
}
