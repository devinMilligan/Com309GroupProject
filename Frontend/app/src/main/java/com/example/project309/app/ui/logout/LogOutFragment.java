package com.example.project309.app.ui.logout;

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
import com.example.project309.app.LoginActivity;
import com.example.project309.app.MainNavigationScreenAdmin;
import com.example.project309.app.MessageBoxBuilder;

public class LogOutFragment extends Fragment {

    private LogOutViewModel logOutViewModel;
    private MessageBoxBuilder message;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModel =
                ViewModelProviders.of(this).get(LogOutViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_log_out, container, false);
        final TextView textView = root.findViewById(R.id.text_log_out);
        logOutViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        message = new MessageBoxBuilder(root.getContext()){

            @Override
            protected void negativeButtonPressed(){

            }
            @Override
            protected void positiveButtonPressed(){
                Intent loggedIn = new Intent(this.context, LoginActivity.class);
                startActivity(loggedIn);
            }

        };

        message.showMessage("Are You Sure You Want To Log Out?", 2);

        return root;
    }
}