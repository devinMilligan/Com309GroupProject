package com.example.project309.app.uiDelivery.logout;

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

public class LogOutFragmentDelivery extends Fragment {

    private LogOutViewModelDelivery logOutViewModelDelivery;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModelDelivery =
                ViewModelProviders.of(this).get(LogOutViewModelDelivery.class);
        View root = inflater.inflate(R.layout.fragment_log_out_delivery, container, false);
        final TextView textView = root.findViewById(R.id.text_log_out_delivery);
        logOutViewModelDelivery.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}