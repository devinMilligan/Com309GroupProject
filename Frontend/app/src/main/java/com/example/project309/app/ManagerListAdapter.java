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

public class ManagerListAdapter extends ArrayAdapter<ManagerProfile> {

    private ArrayList<ManagerProfile> managers;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    public ManagerListAdapter(@NonNull Context context, int resource, ArrayList<ManagerProfile> s) {
        super(context, resource, s);

        this.managers = s;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }


    @Override
    public ManagerProfile getItem(int position) {

        return managers.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(viewResourceId, null);

        if(position < managers.size()) {

            ManagerProfile m = managers.get(position);


            //Gets the textviews in the storelistadapter
            TextView txt1 = (TextView) convertView.findViewById(android.R.id.text1);

            if (m.getName() != null) {
                txt1.setText(m.getName());
            }

            return convertView;
        }
        return null;
    }
}