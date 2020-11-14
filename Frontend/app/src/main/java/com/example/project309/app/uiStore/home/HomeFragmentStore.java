package com.example.project309.app.uiStore.home;

import android.content.Context;
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
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.MainNavigationScreenStore;
import com.example.project309.app.Order;
import com.example.project309.app.OrdersListAdapter;
import com.example.project309.app.StoreListAdapter;

import java.util.ArrayList;

/**
 * This class gets and displays the current orders for that store on the fragment
 *
 * @author Dein Milligan
 */
public class HomeFragmentStore extends Fragment {

    /**
     * {@link HomeViewModelStore} instance that holds the current orders for this store
     */
    private HomeViewModelStore homeViewModelStore;

    /**
     * ListView that displays the orders on the screen
     */
    private ListView lvOrders;
    /**
     * {@link OrdersListAdapter} instance that helps display the orders on the ListView
     */
    private OrdersListAdapter oAdapter;
    /**
     * Array list of {@link Order}s that is local and used to be displayed on the screen
     */
    private ArrayList<Order> aOrders;



    /**
     * Runs on fragment creation and gets and displays all the orders to the user
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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