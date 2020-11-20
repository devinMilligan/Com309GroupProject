package com.example.project309;

import com.example.project309.app.AccountType;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.Menu;
import com.example.project309.app.MenuItem;
import com.example.project309.app.Order;
import com.example.project309.app.Profile;
import com.example.project309.app.Store;
import com.example.project309.app.ui.map.MapsActivity;
import com.example.project309.net_utils.Const;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class TestAlly {


    @Test
    public void testGetListPickup() {
        Store store = new Store();
        Order order = mock(Order.class);
        when(order.getDeliverer()).thenReturn(new Profile(2, "user", "password", AccountType.CUSTOMER_DELIVERER_ACCOUNT, "ally"));
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order);
        orders.add(order);
        orders.add(order);
        orders.add(order);
        ArrayList<Profile> result = store.getListPickup(orders);
        assertEquals(new Profile(2, "user", "password", AccountType.CUSTOMER_DELIVERER_ACCOUNT, "ally"), result.get(0));
    }

    @Test
    public void testGetListPickup2() {
        Store store = new Store();
        Order order = mock(Order.class);
        when(order.getDeliverer()).thenReturn(new Profile(3, "username", "pword", AccountType.CUSTOMER_DELIVERER_ACCOUNT, "ally2"));
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order);
        ArrayList<Profile> result = store.getListPickup(orders);
        assertEquals(new Profile(3, "username", "pword", AccountType.CUSTOMER_DELIVERER_ACCOUNT, "ally2"), result.get(0));
    }

    @Test
    public void testGetThreeStoreRecommendations(){
        MenuItem pizza = new MenuItem("pizza", "cheese, pepperoni",2.00);
        MenuItem drink = new MenuItem("drink", "coke, water", 1.00);
        MenuItem salad = new MenuItem("salad", "cobb, caesar",1.00);

        Order order = new Order(1, 3.00);
        order.addMenuItem(pizza);
        order.addMenuItem(drink);

        Menu menu1 = new Menu();
        menu1.addMenuItem(pizza);
        menu1.addMenuItem(drink);

        Menu menu2 = new Menu();
        menu2.addMenuItem(salad);

        Store store1 = mock(Store.class);
        when(store1.getDistanceFromCurrentLocation()).thenReturn(5.0);
        when(store1.getMenu()).thenReturn(menu2);

        Store store2 = mock(Store.class);
        when(store2.getDistanceFromCurrentLocation()).thenReturn(6.0);
        when(store2.getMenu()).thenReturn(menu1);

        Store store3 = mock(Store.class);
        when(store3.getDistanceFromCurrentLocation()).thenReturn(7.0);
        when(store3.getMenu()).thenReturn(menu1);

        Store store4 = mock(Store.class);
        when(store4.getDistanceFromCurrentLocation()).thenReturn(8.0);
        when(store4.getMenu()).thenReturn(menu1);

        Store store5 = mock(Store.class);
        when(store5.getDistanceFromCurrentLocation()).thenReturn(8.0);
        when(store5.getMenu()).thenReturn(menu2);

        ArrayList<Store> storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);
        storeList.add(store3);
        storeList.add(store4);
        storeList.add(store5);

        ArrayList<Store> stores = order.getThreeStoreRecommendations(storeList);
        assertEquals(store2, stores.get(0));
        assertEquals(store3, stores.get(1));
        assertEquals(store4, stores.get(2));
    }

    @Test
    public void testGetThreeStoreRecommendations2(){
        MenuItem pizza = new MenuItem("pizza", "cheese, pepperoni",2.00);
        MenuItem drink = new MenuItem("drink", "coke, water", 1.00);
        MenuItem salad = new MenuItem("salad", "cobb, caesar",1.00);

        Order order = new Order(1, 3.00);
        order.addMenuItem(pizza);
        order.addMenuItem(drink);

        Menu menu1 = new Menu();
        menu1.addMenuItem(pizza);
        menu1.addMenuItem(drink);

        Menu menu2 = new Menu();
        menu2.addMenuItem(salad);

        Store store1 = mock(Store.class);
        when(store1.getDistanceFromCurrentLocation()).thenReturn(7.0);
        when(store1.getMenu()).thenReturn(menu1);

        Store store2 = mock(Store.class);
        when(store2.getDistanceFromCurrentLocation()).thenReturn(5.0);
        when(store2.getMenu()).thenReturn(menu1);

        Store store3 = mock(Store.class);
        when(store3.getDistanceFromCurrentLocation()).thenReturn(4.0);
        when(store3.getMenu()).thenReturn(menu1);

        Store store4 = mock(Store.class);
        when(store4.getDistanceFromCurrentLocation()).thenReturn(1.0);
        when(store4.getMenu()).thenReturn(menu2);

        ArrayList<Store> storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);
        storeList.add(store3);
        storeList.add(store4);

        ArrayList<Store> stores = order.getThreeStoreRecommendations(storeList);
        assertEquals(store3, stores.get(0));
        assertEquals(store2, stores.get(1));
        assertEquals(store1, stores.get(2));
    }
}
