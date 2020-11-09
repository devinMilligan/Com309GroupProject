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

/**
 * This class Allows for the easy display in a list of Manager in order to display the correct fields of a manager
 *
 * @author Devin Milligan
 */
public class ManagerListAdapter extends ArrayAdapter<ManagerProfile> {

    /**
     * Total list of managers that can be displayed
     */
    private ArrayList<ManagerProfile> managersAll;
    /**
     * Filtered list of managers that is displayed
     */
    private ArrayList<ManagerProfile> managers;
    private LayoutInflater layoutInflater;
    private int viewResourceId;

    /**
     * Constructor that sets the array that needs to be displayed and where to display it
     *
     * @param context the Context on where this will be displayed
     * @param resource
     * @param s the Arraylist of the Profiles to be displayed
     */
    public ManagerListAdapter(@NonNull Context context, int resource, ArrayList<ManagerProfile> s) {
        super(context, resource, s);

        this.managersAll = s;
        this.managers = new ArrayList<>(s);
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = resource;

    }


    /**
     * Reuturns the size of the displayed managers, the filtered one
     *
     * @return size of filtered list
     */
    public int getCount() {
        return managers.size();
    }

    /**
     * Gets a certain instance from the complete list of Profiles
     *
     * @param position the position of the instnace to get from the arrayList
     * @return
     */
    @Override
    public ManagerProfile getItem(int position) {

        return managers.get(position);
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

    /**
     * Gets the filtered results for the managers based pn what is entered in the text field
     *
     * @return current filter to be used
     */
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