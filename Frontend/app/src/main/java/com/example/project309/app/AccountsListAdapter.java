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
 * This class Allows for the easy display in a list of Accounts in order to display the correct fields of an account
 *
 * @author Devin Milligan
 */
public class AccountsListAdapter extends ArrayAdapter<Profile> {

    /**
     * The complete list of {@link Profile} intances that need to be displayed
     */
    private ArrayList<Profile> profiles;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    /**
     * Constructor that sets the array that needs to be displayed and where to display it
     *
     * @param context the Context on where this will be displayed
     * @param resource
     * @param p the Arraylist of the Profiles to be displayed
     */
    public AccountsListAdapter(@NonNull Context context, int resource, ArrayList<Profile> p) {
        super(context, resource, p);

        this.profiles = p;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }


    /**
     * Gets a certain instance from the complete list of Profiles
     *
     * @param position the position of the instnace to get from the arrayList
     * @return
     */
    @Override
    public Profile getItem(int position) {
        return profiles.get(position);
    }

    /**
     * Gets the view with the correct fields to be displayed on the contatiner
     *
     * @param position position of the instance to be displayed
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(viewResourceId, null);

        if(position < profiles.size()) {

            Profile profile = profiles.get(position);


            //Gets the textviews in the storelistadapter
            TextView accountName = (TextView) convertView.findViewById(R.id.txtAccountName);
            TextView accountEmail = (TextView) convertView.findViewById(R.id.txtAccountEmail);

            if (accountName != null) {
                accountName.setText(profile.getName());
            }
            if (accountEmail != null) {     //set the values to the display
                accountEmail.setText(profile.getEmail());
            }

            return convertView;
        }
        return null;
    }
}