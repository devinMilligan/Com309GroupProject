package com.example.project309.app.uiAdmin.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project309.R;
import com.example.project309.app.Store;
import com.example.project309.app.StoreListAdapter;

import java.util.ArrayList;

public class HomeFragmentAdmin extends Fragment {

    private HomeViewModelAdmin homeViewModelAdmin;

    private ListView lvStores;
    private StoreListAdapter sAdapter;
    private ArrayList<Store> aStores;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        homeViewModelAdmin =
                ViewModelProviders.of(this).get(HomeViewModelAdmin.class);
        final View root = inflater.inflate(R.layout.fragment_home_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_home_admin);
        homeViewModelAdmin.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aStores = new ArrayList<>();

        lvStores = (ListView)root.findViewById(R.id.lvStores_admin);

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

}