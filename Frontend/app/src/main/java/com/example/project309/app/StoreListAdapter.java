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
 * This class Allows for the easy display in a list of Stores in order to display the correct fields of a Stores
 *
 * @author Devin Milligan
 */
public class StoreListAdapter extends ArrayAdapter<Store> {

    /**
     * List of all stores to be displayed
     */
    private ArrayList<Store> stores;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    /**
     * Constructor that takes in the list of Stores and Context where this is going to be displayed
     *
     * @param context Context where this is going to be displayed
     * @param resource
     * @param s the list of stores to be displayed
     */
    public StoreListAdapter(@NonNull Context context, int resource, ArrayList<Store> s) {
        super(context, resource, s);

        this.stores = s;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }


    /**
     * Gets an item from the list of stores
     *
     * @param position position of the item from the list to get
     * @return Store intance from list
     */
    @Override
    public Store getItem(int position) {
        return stores.get(position);
    }

    /**
     * Creates a view to be used in a container and returns this view to be displayed
     *
     * @param position the position where this is displayed in a list
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(viewResourceId, null);

        if(position < stores.size()) {

            Store store = stores.get(position);


            //Gets the textviews in the storelistadapter
            TextView storeName = (TextView) convertView.findViewById(R.id.txtStoreName);
            TextView storeDistance = (TextView) convertView.findViewById(R.id.txtStoreLocation);

            if (storeName != null) {
                storeName.setText(store.getName());
            }
            if (storeDistance != null) {     //set the values to the display
                storeDistance.setText(store.getAddress());
            }

            return convertView;
        }
        return null;
    }
}