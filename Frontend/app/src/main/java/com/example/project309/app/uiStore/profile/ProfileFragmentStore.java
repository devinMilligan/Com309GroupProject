package com.example.project309.app.uiStore.profile;

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

public class ProfileFragmentStore extends Fragment {

    private ProfileViewModelStore profileViewModelStore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModelStore =
                ViewModelProviders.of(this).get(ProfileViewModelStore.class);
        View root = inflater.inflate(R.layout.fragment_profile_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_profile_admin);
        profileViewModelStore.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}