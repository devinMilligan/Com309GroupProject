package com.example.project309.app.uiAdmin.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project309.R;
import com.example.project309.app.Create_Update_Store;
import com.example.project309.app.MainNavigationScreen;
import com.example.project309.app.MainNavigationScreenAdmin;
import com.example.project309.app.Store;
import com.example.project309.app.StoreListAdapter;

import java.util.ArrayList;

public class HomeFragmentAdmin extends Fragment implements AdapterView.OnItemClickListener {

    private HomeViewModelAdmin homeViewModelAdmin;

    private ListView lvStores;
    private StoreListAdapter sAdapter;
    private ArrayList<Store> aStores;

    private Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModelAdmin =
                ViewModelProviders.of(this).get(HomeViewModelAdmin.class);
        final View root = inflater.inflate(R.layout.fragment_home_admin, container, false);
        ((MainNavigationScreenAdmin)getActivity()).changeThreeDotFunction(MainNavigationScreenAdmin.SpecialFucntionType.ADD_STORE);

        context = root.getContext();

        final TextView textView = root.findViewById(R.id.text_home_admin);
        homeViewModelAdmin.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aStores = new ArrayList<>();

        lvStores = (ListView)root.findViewById(R.id.lvStores_admin);
        lvStores.setOnItemClickListener(this);

        homeViewModelAdmin.getStores().observe(this, new Observer<ArrayList<Store>>() {
            @Override
            public void onChanged(ArrayList<Store> stores) {

                aStores = stores;
                sAdapter = new StoreListAdapter(root.getContext(), R.layout.store_list_adapter, aStores);
                lvStores.setAdapter(sAdapter);

            }
        });

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Store.currentStore = sAdapter.getItem(position);
        Intent startActivityCreateUpdate = new Intent(context, Create_Update_Store.class);
        startActivityCreateUpdate.putExtra("Create/Update", "Update");
        startActivity(startActivityCreateUpdate);


    }
}