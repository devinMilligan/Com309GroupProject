package com.example.project309.app.uiStore.logout;

import android.content.Context;
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
import com.example.project309.app.AppController;
import com.example.project309.app.LoginActivity;
import com.example.project309.app.MessageBoxBuilder;
import com.example.project309.app.MessageBoxInter;
import com.example.project309.app.MessageBoxListenerInter;

/**
 * This class allows the user to logout and move back to the login screen
 *
 * @author Devin Milligan
 */
public class LogOutFragmentStore extends Fragment implements View.OnClickListener, MessageBoxListenerInter {

    /**
     * {@link LogOutFragmentStore} instance that holds the describing text for the fragment
     */
    private LogOutViewModelStore logOutViewModelStore;
    /**
     * {@link MessageBoxBuilder} instance used to display messages to the user
     */
    private MessageBoxInter message;

    /**
     * Button used to log out
     */
    private Button btnLogOut;

    /**
     * Context used to reference this fragment
     */
    private Context context;

    /**
     * Runs on fragment creation and asks the user if they want to log out
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModelStore =
                ViewModelProviders.of(this).get(LogOutViewModelStore.class);
        View root = inflater.inflate(R.layout.fragment_log_out_store, container, false);
        final TextView textView = root.findViewById(R.id.text_log_out_store);

        btnLogOut = (Button)root.findViewById(R.id.btnLogOut_store);
        btnLogOut.setOnClickListener(this);

        logOutViewModelStore.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        context = root.getContext();
        message = AppController.getInstance().getMessageBoxBuilderInstance();
        message.setContext(context);
        message.setListener(this);

        message.showMessage("Logging Out?", 2);

        message.showMessage("Logging Out?", 2);

        return root;
    }

    /**
     * Recieves all the button clicks and determines what to do with each button
     *
     * @param v View containg the Button id
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnLogOut_store:

                message.showMessage("Logging Out?", 2);


        }

    }

    /**
     * What to do if the Message Box is dismissed
     *
     * @param message the message of the box that was dismissed
     */
    @Override
    public void onDismiss(String message) {

    }

    /**
     * What to do if the ok button is pressed of the message box
     *
     * @param message the message of the box that was utilized
     */
    @Override
    public void neutralButtonPressed(String message) {

    }

    /**
     * What to do if the yes button is pressed on the messages box, this logs the user out and
     * moves them back to the user screen
     *
     * @param message the message of the box that was utlized
     */
    @Override
    public void positiveButtonPressed(String message) {
        Intent loggedIn = new Intent(this.context, LoginActivity.class);
        startActivity(loggedIn);
        getActivity().finish();
    }

    /**
     * What to do if the no button is pressed on the message box
     *
     * @param message the message of the box that was utilized
     */
    @Override
    public void negativeButtonPressed(String message) {

    }
}