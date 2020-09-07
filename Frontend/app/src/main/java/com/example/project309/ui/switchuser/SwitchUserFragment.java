package com.example.project309.ui.switchuser;

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

public class SwitchUserFragment extends Fragment {

    private SwitchUserViewModel switchUserViewModel;

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
        return root;
    }
}