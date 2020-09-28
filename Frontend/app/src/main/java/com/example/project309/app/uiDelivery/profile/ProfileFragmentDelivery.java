package com.example.project309.app.uiDelivery.profile;

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

public class ProfileFragmentDelivery extends Fragment {

    private ProfileViewModelDelivery profileViewModelDelivery;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModelDelivery =
                ViewModelProviders.of(this).get(ProfileViewModelDelivery.class);
        View root = inflater.inflate(R.layout.fragment_profile_delivery, container, false);
        final TextView textView = root.findViewById(R.id.text_profile_delivery);
        profileViewModelDelivery.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}