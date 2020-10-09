package com.example.project309.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.project309.R;

import java.util.ArrayList;

public class OrdersListAdapter extends ArrayAdapter<Order> {

    private ArrayList<Order> orders;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    public OrdersListAdapter(@NonNull Context context, int resource, ArrayList<Order> o) {
        super(context, resource, o);

        this.orders = o;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }


    @Override
    public Order getItem(int position) {
        return orders.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(viewResourceId, null);

        if(position < orders.size()) {

            Order order = orders.get(position);


            //Gets the textviews in the storelistadapter
            TextView orderNum = (TextView) convertView.findViewById(R.id.txtOrderNum);
            TextView orderPrice = (TextView) convertView.findViewById(R.id.txtOrderPrice);

            if (orderNum != null) {
                orderNum.setText(Integer.toString(order.getOrderNumber()));
            }
            if (orderPrice != null) {     //set the values to the display
                orderPrice.setText("Price: $" + Double.toString(order.getOrderPrice()));
            }

            return convertView;
        }
        return null;
    }
}