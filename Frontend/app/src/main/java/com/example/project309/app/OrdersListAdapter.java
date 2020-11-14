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

/**
 * This class Allows for the easy display in a list of Orders in order to display the correct fields of a Order
 *
 * @author Devin Milligan
 */
public class OrdersListAdapter extends ArrayAdapter<Order> {

    /**
     * List of all orders that will be displayed
     */
    private ArrayList<Order> orders;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    /**
     * Constructor that takes in the list of all of the items to be displayed and where to be displayed
     *
     * @param context Context where to be displayed
     * @param resource
     * @param o The list of all the orders that will be displayed
     */
    public OrdersListAdapter(@NonNull Context context, int resource, ArrayList<Order> o) {
        super(context, resource, o);

        this.orders = o;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }

    /**
     * This gets the item in the position on the display
     *
     * @param position the position of the item to be displayed
     * @return
     */
    @Override
    public Order getItem(int position) {
        return orders.get(position);
    }

    /**
     * Sets up a display with the correct fields to be displayed and returnes this View to be displayed
     *
     * @param position the position of the item to be displayed
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(viewResourceId, null);

        if(position < orders.size()) {

            Order order = orders.get(position);


            //Gets the textviews in the storelistadapter
            TextView orderNum = (TextView) convertView.findViewById(R.id.txtOrderNum);
            TextView orderPrice = (TextView) convertView.findViewById(R.id.txtOrderPrice);
            TextView storeName = (TextView)convertView.findViewById(R.id.txtStoreName);

            if (orderNum != null) {
                orderNum.setText(Integer.toString(order.getOrderNumber()));
            }
            if (orderPrice != null) {     //set the values to the display
                orderPrice.setText("Price: $" + Double.toString(order.getOrderPrice()));
            }
            if(storeName != null && order.getStore() != null){
                storeName.setText(order.getStore().getName());
            }

            return convertView;
        }
        return null;
    }
}