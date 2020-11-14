package com.example.project309.app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project309.R;
import com.example.project309.app.MainNavigationScreen;
import com.example.project309.app.MenuFragment;
import com.example.project309.app.Store;
import com.example.project309.app.StoreListAdapter;

import java.util.ArrayList;


/**
 *  HomeFragment controls the Home fragment of the normal user's main screen in order to display
 *  all the stores that they are able to order from.
 *
 * @author Devin Milligan
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

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
        ((MainNavigationScreen)getActivity()).showMenuFragment();


    }
}