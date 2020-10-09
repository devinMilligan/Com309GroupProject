package com.example.project309.app.uiDelivery.home;

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
import com.example.project309.app.Order;
import com.example.project309.app.OrdersListAdapter;
import com.example.project309.app.StoreListAdapter;

import java.util.ArrayList;

public class HomeFragmentDelivery extends Fragment {

    private HomeViewModelDelivery homeViewModelDelivery;

    private ListView lvOrders;
    private OrdersListAdapter oAdapter;
    private ArrayList<Order> aOrders;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        homeViewModelDelivery =
                ViewModelProviders.of(this).get(HomeViewModelDelivery.class);
        final View root = inflater.inflate(R.layout.fragment_home_delivery, container, false);
        final TextView textView = root.findViewById(R.id.text_home_delivery);
        homeViewModelDelivery.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aOrders = new ArrayList<>();

        lvOrders = (ListView)root.findViewById(R.id.lvOrders_delivery);

        homeViewModelDelivery.getOrders().observe(this, new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(ArrayList<Order> orders) {

                aOrders = orders;
                oAdapter = new OrdersListAdapter(root.getContext(), R.layout.order_list_adapter, aOrders);
                lvOrders.setAdapter(oAdapter);

            }
        });

        return root;
    }

}