package com.example.project309.app.uiDelivery.home;

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
import com.example.project309.app.MainNavigationScreenDelivery;
import com.example.project309.app.Store;
import com.example.project309.app.StoreListAdapter;
import com.example.project309.app.ui.home.HomeViewModel;

import java.util.ArrayList;

/**
 * This class displays the current orders available for the Deliverer to pick up
 *
 * @author Devin Milligan
 */
public class HomeFragmentDelivery extends Fragment implements AdapterView.OnItemClickListener {

    /**
     * Contains the {@link HomeViewModel} object that returns the store list
     */
    private HomeViewModel homeViewModel;

    /**
     * The ListView that will display all the stores
     */
    private ListView lvStores;

    /**
     * The {@link StoreListAdapter} object that helps display the {@link Store} objects on the ListView
     */
    private StoreListAdapter sAdapter;

    /**
     * The complete list of stores to be displayed
     */
    private ArrayList<Store> aStores;

    /**
     *  onCreateView runs when the fragment view is created and pulls the most recent list of
     *  stores from the database, never has to be directly called by a user
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aStores = new ArrayList<>();

        lvStores = (ListView)root.findViewById(R.id.lvStores);
        lvStores.setOnItemClickListener(this);

        homeViewModel.getStores().observe(this, new Observer<ArrayList<Store>>() {
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

        Store.currentStore = (Store)parent.getItemAtPosition(position);
        ((MainNavigationScreenDelivery)getActivity()).showOrdersFragment();


    }
/*
    /**
     * {@link HomeViewModelDelivery} instance holding the current orders and the describing text
     *
    private HomeViewModelDelivery homeViewModelDelivery;

    /**
     * ListView used to display the current orders to the user
     *
    private ListView lvOrders;
    /**
     * {@link OrdersListAdapter} instance used to help display the orders on the ListView
     *
    private OrdersListAdapter oAdapter;
    /**
     * ArrayList instance of {@link Order}s that is the local list of orders being displayed
     *
    private ArrayList<Order> aOrders;


    /**
     * Runs on fragment creation and displays all the current orders on the screen and gets the current ones
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     *
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
        lvOrders.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Order.currentOrder = (Order)parent.getItemAtPosition(position);

    }*/
}