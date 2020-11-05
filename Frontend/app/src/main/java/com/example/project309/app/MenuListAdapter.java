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

public class MenuListAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menuItems;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    public MenuListAdapter(@NonNull Context context, int resource, ArrayList<MenuItem> m) {
        super(context, resource, m);

        this.menuItems = m;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }
    public MenuListAdapter(@NonNull Context context, int resource, Menu m) {

        super(context, resource, m.menuItems);

        ArrayList<MenuItem> temp = m.menuItems;

        this.menuItems = temp;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }


    @Override
    public MenuItem getItem(int position) {
        return menuItems.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(viewResourceId, null);

        if(position < menuItems.size()) {

            MenuItem menuItem = menuItems.get(position);


            //Gets the textviews in the storelistadapter
            TextView menuTitle = (TextView) convertView.findViewById(R.id.txtMenuItem);
            TextView menuItemPrice = (TextView) convertView.findViewById(R.id.txtItemPrice);
            TextView menuDescription = (TextView) convertView.findViewById(R.id.txtDescriptionList);

            if (menuTitle != null) {
                menuTitle.setText(menuItem.getTitle());
            }
            if (menuItemPrice != null) {     //set the values to the display
                menuItemPrice.setText(Double.toString(menuItem.getPrice()));
            }
            if(menuDescription != null){
                if(menuItem.getDescription().length() > 50){
                    menuDescription.setText(menuItem.getDescription().substring(0,50) + "...");
                }else {
                    menuDescription.setText(menuItem.getDescription());
                }
            }

            return convertView;
        }
        return null;
    }
}