package com.example.project309;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class StoreListAdapter extends ArrayAdapter<Store> {

    private ArrayList<Store> stores;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    public StoreListAdapter(@NonNull Context context, int resource, ArrayList<Store> s) {
        super(context, resource, s);

        this.stores = s;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }


    @Override
    public Store getItem(int position) {
        return stores.get(position);
    }

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
                storeDistance.setText(store.getLocation());
            }

            return convertView;
        }
        return null;
    }
}