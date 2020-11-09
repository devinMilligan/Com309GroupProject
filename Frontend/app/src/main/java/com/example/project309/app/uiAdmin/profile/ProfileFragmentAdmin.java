package com.example.project309.app.uiAdmin.profile;

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

/**
 * Displays the profile information of the admin and allows edits to the information
 *
 * @author Devin Milligan
 */
public class ProfileFragmentAdmin extends Fragment {
    /**
     * {@link ProfileViewModelAdmin} instance that holds the current admin account information
     */
    private ProfileViewModelAdmin profileViewModelAdmin;

    /**
     * Runs on the creation of the fragment and fills the fields on the screen
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModelAdmin =
                ViewModelProviders.of(this).get(ProfileViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_profile_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_profile_admin);
        profileViewModelAdmin.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}