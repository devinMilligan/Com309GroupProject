package com.example.project309.app.uiDelivery.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project309.R;
import com.example.project309.app.LoginActivity;
import com.example.project309.app.MessageBoxBuilder;

public class LogOutFragmentDelivery extends Fragment implements View.OnClickListener {

    private LogOutViewModelDelivery logOutViewModelDelivery;
    private MessageBoxBuilder message;

    private Button btnLogOut;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModelDelivery =
                ViewModelProviders.of(this).get(LogOutViewModelDelivery.class);
        View root = inflater.inflate(R.layout.fragment_log_out_delivery, container, false);
        final TextView textView = root.findViewById(R.id.text_log_out_delivery);

        btnLogOut = (Button)root.findViewById(R.id.btnLogOut_delivery);
        btnLogOut.setOnClickListener(this);

        logOutViewModelDelivery.getText().observe(this, new Observer<String>() {
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
                getActivity().finish();
            }

        };

        message.showMessage("Logging Out?", 2);

        return root;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnLogOut_delivery:

                message.showMessage("Logging Out?", 2);


        }

    }
}