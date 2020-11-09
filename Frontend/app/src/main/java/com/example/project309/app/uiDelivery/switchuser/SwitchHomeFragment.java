package com.example.project309.app.uiDelivery.switchuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project309.R;
import com.example.project309.app.MainNavigationScreen;
import com.example.project309.app.MainNavigationScreenDelivery;

/**
 * This class allows the user as a Deliverer to switch back to the customer view
 *
 * @author Devin Milligan
 */
public class SwitchHomeFragment extends Fragment {

    /**
     * {@link SwitchHomeViewModel} instance that holds the describing text for the fragment
     */
    private SwitchHomeViewModel switchHomeViewModel;

    /**
     * Runs on fragment creation and moves the user back to the customer view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        switchHomeViewModel =
                ViewModelProviders.of(this).get(SwitchHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_switch_home_delivery, container, false);
        final TextView textView = root.findViewById(R.id.text_switch_home_delivery);
        switchHomeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        getActivity().finish();

        return root;
    }


}