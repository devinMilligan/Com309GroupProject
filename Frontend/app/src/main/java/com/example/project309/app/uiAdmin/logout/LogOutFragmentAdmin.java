package com.example.project309.app.uiAdmin.logout;

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
 * This class allows the user to lod out and them return to the login screen
 *
 * @author Devin Miligan
 */
public class LogOutFragmentAdmin extends Fragment implements View.OnClickListener, MessageBoxListenerInter {

    /**
     * {@link LogOutViewModelAdmin} instance holding the describing text for this fragment
     */
    private LogOutViewModelAdmin logOutViewModelAdmin;

    /**
     * {@link MessageBoxBuilder} instance used to created message on screen to the user
     */
    private MessageBoxInter message;

    /**
     * Button to logout on the fragment
     */
    private Button btnLogOut;

    /**
     * Context to reference this fragment
     */
    private Context context;

    /**
     * Runs when the fragment is created and asks the user if they want to log out
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModelAdmin =
                ViewModelProviders.of(this).get(LogOutViewModelAdmin.class);
        View root = inflater.inflate(R.layout.fragment_log_out_admin, container, false);
        final TextView textView = root.findViewById(R.id.text_log_out_admin);

        btnLogOut = (Button)root.findViewById(R.id.btnLogOut_admin);
        btnLogOut.setOnClickListener(this);

        logOutViewModelAdmin.getText().observe(this, new Observer<String>() {
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

        return root;
    }

    /**
     * Receives any button clicks and determines what to do based on the button id
     *
     * @param v View of the button that was clicked
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnLogOut_admin:

                message.showMessage("Logging Out?", 2);


        }

    }

    /**
     * What to do if the message box was dismissed
     *
     * @param message the message of the box that was dismissed
     */
    @Override
    public void onDismiss(String message) {

    }

    /**
     * What to do if the ok button is pressed on the message box
     *
     * @param message the message of teh box that was utilized
     */
    @Override
    public void neutralButtonPressed(String message) {

    }

    /**
     * What to do if the yes button was pressed on the message box, this logs the user out and puts
     * them back at the login screen
     *
     * @param message the message of the box that was utilized
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