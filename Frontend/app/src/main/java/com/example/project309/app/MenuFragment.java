package com.example.project309.app;

import androidx.appcompat.view.menu.MenuAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project309.R;
import com.example.project309.app.uiStore.home.HomeViewModelStore;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuFragment extends Fragment {

    private MenuViewModel mViewModel;

    private ListView lvMenu;
    private MenuListAdapter menuAdapter;
    private ArrayList<MenuItem> items = new ArrayList<>();

    private TextView txtName;

    private Context context;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        items.clear();
        mViewModel =
                ViewModelProviders.of(this).get(MenuViewModel.class);
        final View root = inflater.inflate(R.layout.menu_fragment, container, false);

        context = root.getContext();

        lvMenu = (ListView)root.findViewById(R.id.lvMenu);
        menuAdapter = new MenuListAdapter(context,R.layout.menu_list_adapter ,items);
        lvMenu.setAdapter(menuAdapter);

        txtName = (TextView)root.findViewById(R.id.text_menu_fragment);
        txtName.setText("Menu: " + Store.currentStore.getName());

        mViewModel.getMenuItems().observe(this, new Observer<ArrayList<MenuItem>>() {

            @Override
            public void onChanged(ArrayList<MenuItem> itemsM) {

                items = itemsM;
                menuAdapter.clear();
                menuAdapter.addAll(items);
                menuAdapter.notifyDataSetChanged();

            }
        });

        return root;
    }

    public static void updateView(ArrayList<MenuItem> itemsM){

       /* items = itemsM;
        menuAdapter.clear();
        menuAdapter.addAll(items);
        menuAdapter.notifyDataSetChanged();*/

    }

}