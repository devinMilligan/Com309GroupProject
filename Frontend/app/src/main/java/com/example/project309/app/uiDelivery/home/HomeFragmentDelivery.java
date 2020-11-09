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

/**
 * This class displays the current orders available for the Deliverer to pick up
 *
 * @author Devin Milligan
 */
public class HomeFragmentDelivery extends Fragment {

    /**
     * {@link HomeViewModelDelivery} instance holding the current orders and the describing text
     */
    private HomeViewModelDelivery homeViewModelDelivery;

    /**
     * ListView used to display the current orders to the user
     */
    private ListView lvOrders;
    /**
     * {@link OrdersListAdapter} instance used to help display the orders on the ListView
     */
    private OrdersListAdapter oAdapter;
    /**
     * ArrayList instance of {@link Order}s that is the local list of orders being displayed
     */
    private ArrayList<Order> aOrders;


    /**
     * Runs on fragment creation and displays all the current orders on the screen and gets the current ones
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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