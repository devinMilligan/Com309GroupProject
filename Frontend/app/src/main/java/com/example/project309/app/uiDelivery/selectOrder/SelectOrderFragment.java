package com.example.project309.app.uiDelivery.selectOrder;

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
import com.example.project309.app.AppController;
import com.example.project309.app.MainNavigationScreenDelivery;
import com.example.project309.app.MessageBoxInter;
import com.example.project309.app.MessageBoxListenerInter;
import com.example.project309.app.Order;
import com.example.project309.app.OrdersListAdapter;
import com.example.project309.app.uiDelivery.viewOrders.OrderListViewModel;

import java.util.ArrayList;

public class SelectOrderFragment extends Fragment implements AdapterView.OnItemClickListener, MessageBoxListenerInter{
    /**
     * Contains the {@link OrderListViewModel} object that returns the store list
     */
    private OrderListViewModel orderListViewModel;

    /**
     * The ListView that will display all the stores
     */
    private ListView lvOrders;

    /**
     * The {@link OrdersListAdapter} object that helps display the {@link Order} objects on the ListView
     */
    private OrdersListAdapter oAdapter;

    /**
     * The complete list of stores to be displayed
     */
    private ArrayList<Order> aOrders;

    private MessageBoxInter message;

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

        orderListViewModel =
                ViewModelProviders.of(this).get(OrderListViewModel.class);
        final View root = inflater.inflate(R.layout.activity_deliver_confirm_order, container, false);
        final TextView textView = root.findViewById(R.id.text_home_delivery);
        orderListViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aOrders = new ArrayList<>();

        lvOrders = (ListView)root.findViewById(R.id.lvOrders_delivery);
        lvOrders.setOnItemClickListener(this);

        orderListViewModel.getOrders().observe(this, new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(ArrayList<Order> orders) {

                aOrders = orders;
                oAdapter = new OrdersListAdapter(root.getContext(), R.layout.order_list_adapter, aOrders);
                lvOrders.setAdapter(oAdapter);

            }
        });

        message = AppController.getInstance().getMessageBoxBuilderInstance();
        message.setContext(root.getContext());
        message.setListener(this);

        return root;
    }

    /**
     *
     * @param message the message of the box that is being utilized
     */
    @Override
    public void onDismiss(String message) {

    }
    /**
     *
     * @param message the message of the box that is being utilized
     */
    @Override
    public void neutralButtonPressed(String message) {

    }
    /**
     *
     * @param message the message of the box that is being utilized
     */
    @Override
    public void positiveButtonPressed(String message) {
        ((MainNavigationScreenDelivery)getActivity()).selectOrderFragment();
    }
    /**
     *
     * @param message the message of the box that is being utilized
     */
    @Override
    public void negativeButtonPressed(String message) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Order.currentOrder = (Order)parent.getItemAtPosition(position);

        message.showMessage("Would you like to accept order #" + Order.currentOrder.getOrderNumber() + "?", 2);

    }
}
