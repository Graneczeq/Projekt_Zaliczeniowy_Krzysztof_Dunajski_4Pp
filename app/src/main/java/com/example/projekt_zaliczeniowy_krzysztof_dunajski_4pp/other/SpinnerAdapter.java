package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private final Context context;
    private final List<Item> itemList;

    public SpinnerAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList != null ? itemList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.dropdown_item, viewGroup, false);

        ImageView itemImage = rootView.findViewById(R.id.itemImage);
        TextView itemName = rootView.findViewById(R.id.itemName);
        TextView itemPrice = rootView.findViewById(R.id.itemPrice);
        TextView itemDesc = rootView.findViewById(R.id.itemDesc);

        itemImage.setImageResource(itemList.get(i).getImage());
        itemName.setText(itemList.get(i).getName());
        itemPrice.setText(itemList.get(i).getPrice());
        itemDesc.setText(itemList.get(i).getDescription());

        return rootView;
    }
}