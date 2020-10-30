package com.example.project309.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ManagerListAdapter extends ArrayAdapter<ManagerProfile> {

    private ArrayList<ManagerProfile> managersAll;
    private ArrayList<ManagerProfile> managers;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    public ManagerListAdapter(@NonNull Context context, int resource, ArrayList<ManagerProfile> s) {
        super(context, resource, s);

        this.managersAll = s;
        this.managers = new ArrayList<>(s);
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }

    public int getCount() {
        return managers.size();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((ManagerProfile) resultValue).getName();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<ManagerProfile> managerSuggestion = new ArrayList<>();
                if (constraint != null) {
                    for (ManagerProfile manager : managersAll) {
                        if (manager.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            managerSuggestion.add(manager);
                        }
                    }
                    filterResults.values = managerSuggestion;
                    filterResults.count = managerSuggestion.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                managers.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    for (Object object : (List<?>) results.values) {
                        if (object instanceof ManagerProfile) {
                            managers.add((ManagerProfile) object);
                        }
                    }
                    notifyDataSetChanged();
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    managers.addAll(managersAll);
                    notifyDataSetInvalidated();
                }
            }
        };
    }
}