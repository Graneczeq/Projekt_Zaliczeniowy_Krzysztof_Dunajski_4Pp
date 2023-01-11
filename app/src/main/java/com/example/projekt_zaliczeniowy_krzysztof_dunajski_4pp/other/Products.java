package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.other;

import android.content.Context;
import android.content.res.Resources;

import com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.OrderMenu;
import com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.R;

import java.util.ArrayList;
import java.util.List;

public class Products {
    Context context;
    String[] keyboardNames, keyboardPrices, keyboardDescriptions, mouseNames, mousePrices, mouseDescriptions, monitorNames, monitorPrices, monitorDescriptions, computerNames, computerPrices, computerDescriptions;
    int[] keyboardImages = {
            R.drawable.keyboard1,
            R.drawable.keyboard2,
            R.drawable.keyboard3,
    };
    int[] mouseImages = {
            R.drawable.mouse1,
            R.drawable.mouse2,
            R.drawable.mouse3
    };
    int[] monitorImages = {
            R.drawable.monitor1,
            R.drawable.monitor2,
            R.drawable.monitor3
    };
    int[] computerImages = {
            R.drawable.computer1,
            R.drawable.computer2,
            R.drawable.computer3
    };
    Resources resources;

    public Products(Context context, Resources resources) {
        this.context = context;
        this.resources = resources;
    }

    public float getKeyboardPrice(int index) {
        keyboardPrices = resources.getStringArray(R.array.keyboardPrices);
        return Float.parseFloat(keyboardPrices[index].substring(1));
    }

    public List<Item> getKeyboardList() {
        keyboardNames = resources.getStringArray(R.array.keyboardNames);
        keyboardPrices = resources.getStringArray(R.array.keyboardPrices);
        keyboardDescriptions = resources.getStringArray(R.array.keyboardDescriptions);

        List<Item> keyboardList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Item item = new Item(keyboardNames[i], keyboardPrices[i], keyboardDescriptions[i], keyboardImages[i]);
            keyboardList.add(item);
        }

        return keyboardList;
    }

    public float getMousePrice(int index) {
        mousePrices = resources.getStringArray(R.array.mousePrices);
        return Float.parseFloat(mousePrices[index].substring(1));
    }

    public List<Item> getMouseList() {
        mouseNames = resources.getStringArray(R.array.mouseNames);
        mousePrices = resources.getStringArray(R.array.mousePrices);
        mouseDescriptions = resources.getStringArray(R.array.mouseDescriptions);

        List<Item> mouseList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Item item = new Item(mouseNames[i], mousePrices[i], mouseDescriptions[i], mouseImages[i]);
            mouseList.add(item);
        }

        return mouseList;
    }

    public float getMonitorPrice(int index) {
        monitorPrices = resources.getStringArray(R.array.monitorPrices);
        return Float.parseFloat(monitorPrices[index].substring(1));
    }

    public List<Item> getMonitorList() {
        monitorNames = resources.getStringArray(R.array.monitorNames);
        monitorPrices = resources.getStringArray(R.array.monitorPrices);
        monitorDescriptions = resources.getStringArray(R.array.monitorDescriptions);

        List<Item> monitorList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Item item = new Item(monitorNames[i], monitorPrices[i], monitorDescriptions[i], monitorImages[i]);
            monitorList.add(item);
        }

        return monitorList;
    }

    public float getComputerPrice(int index) {
        computerPrices = resources.getStringArray(R.array.computerPrices);
        return Float.parseFloat(computerPrices[index].substring(1));
    }

    public List<Item> getComputerList() {
        computerNames = resources.getStringArray(R.array.computerNames);
        computerPrices = resources.getStringArray(R.array.computerPrices);
        computerDescriptions = resources.getStringArray(R.array.computerDescriptions);

        List<Item> computerList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Item item = new Item(computerNames[i], computerPrices[i], computerDescriptions[i], computerImages[i]);
            computerList.add(item);
        }

        return computerList;
    }
}
