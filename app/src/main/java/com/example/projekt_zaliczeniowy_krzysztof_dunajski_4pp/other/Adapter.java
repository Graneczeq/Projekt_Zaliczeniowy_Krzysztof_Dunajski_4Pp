package com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt_zaliczeniowy_krzysztof_dunajski_4pp.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<String> keyboardNames, mouseNames, monitorNames, computerNames, orderDates;
    ArrayList<Float> keyboardPrices, mousePrices, monitorPrices, computerPrices, orderPrices;
    ArrayList<Integer> orderIds;

    public Adapter(ArrayList<String> keyboardNames, ArrayList<String> mouseNames, ArrayList<String> monitorNames, ArrayList<String> computerNames, ArrayList<Float> keyboardPrices, ArrayList<Float> mousePrices, ArrayList<Float> monitorPrices, ArrayList<Float> computerPrices, ArrayList<Float> orderPrices, ArrayList<String> orderDates, ArrayList<Integer> orderIds) {
        this.keyboardNames = keyboardNames;
        this.mouseNames = mouseNames;
        this.monitorNames = monitorNames;
        this.computerNames = computerNames;
        this.keyboardPrices = keyboardPrices;
        this.mousePrices = mousePrices;
        this.monitorPrices = monitorPrices;
        this.computerPrices = computerPrices;
        this.orderPrices = orderPrices;
        this.orderDates = orderDates;
        this.orderIds = orderIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_holder, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.computerName.getContext();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
        params.setMargins(0, 0, 0, 0);
        holder.orderId.setText(context.getString(R.string.order) + " " + orderIds.get(position).toString());
        holder.orderDate.setText(orderDates.get(position));
        holder.orderPrice.setText(context.getString(R.string.order_price) + ": $" + orderPrices.get(position).toString());
        if(!keyboardNames.get(position).equals("")) {
            holder.keyboardName.setText(context.getString(R.string.keyboard) + ": " + keyboardNames.get(position));
            holder.keyboardPrice.setText("$" + keyboardPrices.get(position).toString());
        } else {
            LinearLayout keyboardDataParent = (LinearLayout) holder.keyboardName.getParent();
            keyboardDataParent.setLayoutParams(params);
        }
        if(!mouseNames.get(position).equals("")) {
            holder.mouseName.setText(context.getString(R.string.mouse) + ": " + mouseNames.get(position));
            holder.mousePrice.setText("$" + mousePrices.get(position).toString());
        } else {
            LinearLayout mouseDataParent = (LinearLayout) holder.mouseName.getParent();
            mouseDataParent.setLayoutParams(params);
        }
        if(!monitorNames.get(position).equals("")) {
            holder.monitorName.setText(context.getString(R.string.monitor) + ": " + monitorNames.get(position));
            holder.monitorPrice.setText("$" + monitorPrices.get(position).toString());
        } else {
            LinearLayout monitorDataParent = (LinearLayout) holder.monitorName.getParent();
            monitorDataParent.setLayoutParams(params);
        }
        if(!computerNames.get(position).equals("")) {
            holder.computerName.setText(context.getString(R.string.computer) + ": " + computerNames.get(position));
            holder.computerPrice.setText("$" + computerPrices.get(position).toString());
        } else {
            LinearLayout monitorDataParent = (LinearLayout) holder.monitorName.getParent();
            monitorDataParent.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return orderIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView keyboardName, keyboardPrice, mouseName, mousePrice, monitorName, monitorPrice, computerName, computerPrice, orderDate, orderId, orderPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            keyboardName = itemView.findViewById(R.id.keyboardName);
            keyboardPrice = itemView.findViewById(R.id.keyboardPrice);
            mouseName = itemView.findViewById(R.id.mouseName);
            mousePrice = itemView.findViewById(R.id.mousePrice);
            monitorName = itemView.findViewById(R.id.monitorName);
            monitorPrice = itemView.findViewById(R.id.monitorPrice);
            computerName = itemView.findViewById(R.id.computerName);
            computerPrice = itemView.findViewById(R.id.computerPrice);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderId = itemView.findViewById(R.id.orderId);
            orderPrice = itemView.findViewById(R.id.orderPrice);
        }
    }
}