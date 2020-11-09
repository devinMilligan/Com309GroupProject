package com.example.project309.app.ui.switchuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project309.R;
import com.example.project309.app.MainNavigationScreenAdmin;
import com.example.project309.app.MainNavigationScreenDelivery;

/**
 * This class handles the switching of view from the customer view to the view of a deliverer
 *
 * @author Devin Milligan
 */
public class SwitchUserFragment extends Fragment {

    /**
     * {@link SwitchUserViewModel} that holds the live value of the describing text
     */
    private SwitchUserViewModel switchUserViewModel;

    /**
     * Runs on the fragment creation and puts the user into delivery view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        switchUserViewModel =
                ViewModelProviders.of(this).get(SwitchUserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_switch_user, container, false);
        final TextView textView = root.findViewById(R.id.text_switch_user);
        switchUserViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Intent loggedIn = new Intent(root.getContext(), MainNavigationScreenDelivery.class);
        startActivity(loggedIn);


        return root;
    }


}