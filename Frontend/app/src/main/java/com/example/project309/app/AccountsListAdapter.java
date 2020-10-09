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

public class AccountsListAdapter extends ArrayAdapter<Profile> {

    private ArrayList<Profile> profiles;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    public AccountsListAdapter(@NonNull Context context, int resource, ArrayList<Profile> p) {
        super(context, resource, p);

        this.profiles = p;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }


    @Override
    public Profile getItem(int position) {
        return profiles.get(position);
    }

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
                accountEmail.setText(profile.getUserName());
            }

            return convertView;
        }
        return null;
    }
}