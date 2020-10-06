package com.example.project309.app.uiStore.home;

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
import com.example.project309.app.MainNavigationScreenStore;
import com.example.project309.app.Order;
import com.example.project309.app.OrdersListAdapter;
import com.example.project309.app.StoreListAdapter;

import java.util.ArrayList;

public class HomeFragmentStore extends Fragment {

    private HomeViewModelStore homeViewModelStore;

    private ListView lvOrders;
    private OrdersListAdapter oAdapter;
    private ArrayList<Order> aOrders;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ((MainNavigationScreenStore)getActivity()).changeThreeDotFunction(MainNavigationScreenStore.SpecialFucntionType.EDIT_ORDER);

        homeViewModelStore =
                ViewModelProviders.of(this).get(HomeViewModelStore.class);
        final View root = inflater.inflate(R.layout.fragment_home_store, container, false);
        final TextView textView = root.findViewById(R.id.text_home_store);
        homeViewModelStore.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aOrders = new ArrayList<>();

        lvOrders = (ListView)root.findViewById(R.id.lvOrders_store);

        homeViewModelStore.getOrders().observe(this, new Observer<ArrayList<Order>>() {
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