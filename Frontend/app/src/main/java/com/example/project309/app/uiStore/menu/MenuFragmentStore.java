package com.example.project309.app.uiStore.menu;

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
import com.example.project309.app.MainNavigationScreen;
import com.example.project309.app.MainNavigationScreenStore;
import com.example.project309.app.Menu;
import com.example.project309.app.MenuItem;
import com.example.project309.app.MenuListAdapter;
import com.example.project309.app.OrdersListAdapter;

import java.util.ArrayList;

public class MenuFragmentStore extends Fragment {

    private MenuViewModelStore menuViewModelStore;

    private ListView lvMenuItems;
    private MenuListAdapter mAdapter;
    private ArrayList<MenuItem> aMenuItem;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ((MainNavigationScreenStore)getActivity()).changeThreeDotFunction(MainNavigationScreenStore.SpecialFucntionType.EDIT_MENU);

        menuViewModelStore =
                ViewModelProviders.of(this).get(MenuViewModelStore.class);
        final View root = inflater.inflate(R.layout.fragment_menu_store, container, false);
        final TextView textView = root.findViewById(R.id.text_menu_store);
        menuViewModelStore.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aMenuItem = new ArrayList<>();

        lvMenuItems = (ListView)root.findViewById(R.id.lvMenuItems_store);

        menuViewModelStore.getMenuItems().observe(this, new Observer<ArrayList<MenuItem>>() {
            @Override
            public void onChanged(ArrayList<MenuItem> orders) {

                aMenuItem = orders;
                mAdapter = new MenuListAdapter(root.getContext(), R.layout.menu_list_adapter, aMenuItem);
                lvMenuItems.setAdapter(mAdapter);

            }
        });

        return root;
    }



}