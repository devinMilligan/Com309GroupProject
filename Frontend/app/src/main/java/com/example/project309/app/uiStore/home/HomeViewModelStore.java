package com.example.project309.app.uiStore.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project309.app.Order;
import com.example.project309.app.Store;

import java.util.ArrayList;

public class HomeViewModelStore extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Order>> orders;

    public HomeViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Dining Centers");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<Order>> getOrders(){

        if(orders == null){

            orders = new MutableLiveData<ArrayList<Order>>();

            loadOrders();

        }
        return orders;

    }

    private void loadOrders(){

        ArrayList<Order> aStore = new ArrayList<>();
        aStore.add(new Order(1232,19.99));
        aStore.add(new Order(1233,20.00));

        orders.setValue(aStore);

    }

}