package com.example.project309.app.uiDelivery.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Order;
import com.example.project309.app.Store;

import java.util.ArrayList;

/**
 * This class holds the current orders and gets them if necessary and holds the describing text for the fragment
 *
 * @author Devin Milligan
 */
public class HomeViewModelDelivery extends ViewModel {

    /**
     * Holds the current describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * Holds the local list of all the current orders
     */
    private MutableLiveData<ArrayList<Order>> orders;

    /**
     * Default Constructor that sets the current describing text
     */
    public HomeViewModelDelivery() {
        mText = new MutableLiveData<>();
        mText.setValue("Orders");
    }

    /**
     * Returns the current describing text for the fragment
     *
     * @return LiveData instance holding the current text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Determines if the list of orders has been collected and if not calls to collect them
     *
     * @return LiveData instance of the list of {@link Order}s
     */
    public LiveData<ArrayList<Order>> getOrders(){

        if(orders == null){

            orders = new MutableLiveData<ArrayList<Order>>();

            loadOrders();

        }
        return orders;

    }

    /**
     * Collects the complete list of orders from the server
     */
    private void loadOrders(){

        ArrayList<Order> aStore = new ArrayList<>();
        aStore.add(new Order(1232,19.99));
        aStore.add(new Order(1233,20.00));

        orders.setValue(aStore);

    }

}