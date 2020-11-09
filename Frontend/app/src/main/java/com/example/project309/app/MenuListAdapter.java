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
 * This class Allows for the easy display in a list of menu and menu items in order to display the correct fields of a menu items
 *
 * @author Devin Milligan
 */
public class MenuListAdapter extends ArrayAdapter<MenuItem> {

    /**
     * The total list of menu items to be displayed
     */
    private ArrayList<MenuItem> menuItems;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    /**
     * Constructor that sets the total list of things to be displayed
     *
     * @param context the context on where this is to be displayed
     * @param resource
     * @param m the list of Menu Items
     */
    public MenuListAdapter(@NonNull Context context, int resource, ArrayList<MenuItem> m) {
        super(context, resource, m);

        this.menuItems = m;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }

    /**
     * Constructor that takes a Menu object and turn that into a list of items to be displayed
     *
     * @param context the context on where this is to be displayed
     * @param resource
     * @param m the lsit of Menu Items
     */
    public MenuListAdapter(@NonNull Context context, int resource, Menu m) {

        super(context, resource, m.menuItems);

        ArrayList<MenuItem> temp = m.menuItems;

        this.menuItems = temp;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }

    /**
     * Gets the item from the array of total menu items
     *
     * @param position the position of the item to get
     * @return
     */
    @Override
    public MenuItem getItem(int position) {
        return menuItems.get(position);
    }

    /**
     * This sets the fields to be displayed and then returns this view to be used in the display
     *
     * @param position position of the item to be displayed
     * @param convertView
     * @param parent
     * @return
     */
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