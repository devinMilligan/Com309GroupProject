package com.example.project309.app.uiAdmin.AccountsAdmin;

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
import com.example.project309.app.AccountsListAdapter;
import com.example.project309.app.Profile;

import java.util.ArrayList;

public class AccountsFragmentAdmin extends Fragment {

    private AccountsViewModelAdmin accountsViewModelAdmin;

    private ListView lvAdminAccts;
    private AccountsListAdapter aAdapter;
    private ArrayList<Profile> aAdminAccts;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        accountsViewModelAdmin =
                ViewModelProviders.of(this).get(AccountsViewModelAdmin.class);
        final View root = inflater.inflate(R.layout.fragment_accounts_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_accounts_admin);
        accountsViewModelAdmin.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        aAdminAccts = new ArrayList<>();

        lvAdminAccts = (ListView)root.findViewById(R.id.lvAccounts_admin);

        accountsViewModelAdmin.getAdminAccts().observe(this,  new Observer<ArrayList<Profile>>() {
            @Override
            public void onChanged(ArrayList<Profile> profiles) {

                aAdminAccts = profiles;
                aAdapter = new AccountsListAdapter(root.getContext(), R.layout.account_list_adapter, aAdminAccts);
                lvAdminAccts.setAdapter(aAdapter);

            }
        });

        return root;
    }

}