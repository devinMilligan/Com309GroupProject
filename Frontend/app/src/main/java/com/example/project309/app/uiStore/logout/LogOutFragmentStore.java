package com.example.project309.app.uiStore.logout;

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

public class LogOutFragmentStore extends Fragment {

    private LogOutViewModelStore logOutViewModelStore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModelStore =
                ViewModelProviders.of(this).get(LogOutViewModelStore.class);
        View root = inflater.inflate(R.layout.fragment_log_out_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_log_out_admin);
        logOutViewModelStore.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}