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

/**
 * This class displays the Admin Accounts on the fragment
 *
 * @author Devin Milligan
 */
public class AccountsFragmentAdmin extends Fragment {

    /**
     * {@link AccountsViewModelAdmin} instance used to get the admin accounts
     */
    private AccountsViewModelAdmin accountsViewModelAdmin;

    /**
     * ListView used to display the admin accounts
     */
    private ListView lvAdminAccts;
    /**
     * {@link AccountsListAdapter} used to help display the admin accounts in a ListView
     */
    private AccountsListAdapter aAdapter;
    /**
     * ArrayList of all the Admin Accounts
     */
    private ArrayList<Profile> aAdminAccts;


    /**
     * Runs on fragment creation and displays the initial admin accounts after getting them from the AccountsViewModelAdmin
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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