package com.example.project309.app.uiStore.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Order;
import com.example.project309.app.Store;

import java.util.ArrayList;

/**
 * This class holds the current orders for the store and the describing text for the fragement
 *
 * @author Devin Milligan
 */
public class HomeViewModelStore extends ViewModel {

    /**
     * This holds the describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * List of the current orders for the store
     */
    private MutableLiveData<ArrayList<Order>> orders;

    /**
     * Default Constructor that sets the describing text for the fragment
     */
    public HomeViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Dining Centers");
    }

    /**
     * Returns the describing text for the fragment
     *
     * @return LiveData instance contatining the describing text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Determines if the orders for this store have been collected and gets them if not and then returns them
     *
     * @return LiveData instance of the {@link Order}s related to this store
     */
    public LiveData<ArrayList<Order>> getOrders(){

        if(orders == null){

            orders = new MutableLiveData<ArrayList<Order>>();

            loadOrders();

        }
        return orders;

    }

    /**
     * Retrieves the orders from the server
     */
    private void loadOrders(){

        ArrayList<Order> aStore = new ArrayList<>();
        aStore.add(new Order(1232,19.99));
        aStore.add(new Order(1233,20.00));

        orders.setValue(aStore);

    }

}