package com.example.project309.app.uiAdmin.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project309.R;
import com.example.project309.app.AdminProfile;
import com.example.project309.app.Profile;

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

    private EditText edFirst, edLast, edEmail, edPass, edRePass;

    private AdminProfile adminProfile;
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
        adminProfile = ((AdminProfile)Profile.currentLogin);

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

        edFirst = (EditText)root.findViewById(R.id.editFirst_admin);
        edFirst.setText(adminProfile.getFirstName());
        edLast = (EditText)root.findViewById(R.id.editLast_admin);
        edLast.setText(adminProfile.getLastName());
        edEmail = (EditText)root.findViewById(R.id.editEmail_admin);
        edEmail.setText(adminProfile.getEmail());
        edPass = (EditText)root.findViewById(R.id.editPass_admin);
        edPass.setText(adminProfile.getPassword());
        edRePass = (EditText)root.findViewById(R.id.editRePass_Admin);
        edRePass.setText("");

        return root;
    }
}