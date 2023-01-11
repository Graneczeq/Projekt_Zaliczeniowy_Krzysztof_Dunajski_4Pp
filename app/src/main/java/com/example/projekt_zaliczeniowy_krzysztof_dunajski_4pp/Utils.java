package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.Locale;

public class Utils {
    Context context;

    public Utils(Context context) {
        this.context = context;
    }


    public void openAllOrdersActivity() {
        Intent intent = new Intent(context, OrderList.class);
        context.startActivity(intent);
    }

    public void openOrderActivity() {
        Intent intent = new Intent(context, OrderMenu.class);
        context.startActivity(intent);
    }

    public void openAuthorActivity() {
        Intent intent = new Intent(context, AuthorInfo.class);
        context.startActivity(intent);
    }

    public void openLoginActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void resetUserId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        if(sharedPreferences.contains("userId")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("userId");
            editor.apply();
        }
    }

    public boolean redirectIfSavedLoginInfo() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        boolean saving = sharedPreferences.getBoolean("saveLoginInfo", false);
        if(saving) {
            openOrderActivity();
            MainActivity.loginActivity.finish();
            return true;
        }
        return false;
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        locale.setDefault(locale);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
    }

    public String getLocaleInfo() {
        SharedPreferences preferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        return preferences.getString("locale", "pl");
    }


    public int getUserId() {
        SharedPreferences preferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userId");
        editor.putBoolean("saveLoginInfo", false);
        editor.apply();
        Toast.makeText(context,"Informacje logowania zostały usunięte.", Toast.LENGTH_SHORT).show();
    }

}
