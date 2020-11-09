package com.example.project309.app.ui.logout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project309.R;
import com.example.project309.app.AppController;
import com.example.project309.app.LoginActivity;
import com.example.project309.app.MainNavigationScreenAdmin;
import com.example.project309.app.MessageBoxBuilder;
import com.example.project309.app.MessageBoxInter;
import com.example.project309.app.MessageBoxListenerInter;

/**
 * LogOutFragment is used to log the current user out of the app and bring them back to the login screen
 *
 * @author Devin Milligan
 */
public class LogOutFragment extends Fragment implements View.OnClickListener, MessageBoxListenerInter {

    /**
     * The LogoutViewModel that is used to get the describing text for this class
     */
    private LogOutViewModel logOutViewModel;

    /**
     * MessageBox instance used to easily display messages on the screen to the user
     */
    private MessageBoxInter message;

    /**
     * Context used for reference to this fragment
     */
    private Context context;

    /**
     * Button on the fragment to be used to logout
     */
    private Button btnLogOut;

    /**
     * OnCreateView runs when the fragment is created and gets the values from LogOutViewModel and
     * initializes all the local vars. Asks the user if they want to log out
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logOutViewModel =
                ViewModelProviders.of(this).get(LogOutViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_log_out, container, false);
        final TextView textView = root.findViewById(R.id.text_log_out);

        btnLogOut = (Button)root.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);

        logOutViewModel.getText().observe(this, new Observer<String>() {
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
     * Recieves the clicks on any buttons on the screen and determines what to do from the button
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnLogOut:

                message.showMessage("Logging Out?", 2);


        }

    }

    /**
     * What to do if the message box is dismised
     *
     * @param message the message of the box that was dismissed
     */
    @Override
    public void onDismiss(String message) {

    }

    /**
     * What to do if the message box user clicks ok
     *
     * @param message the message of the box that was utilized
     */
    @Override
    public void neutralButtonPressed(String message) {

    }

    /**
     * What to do if the yes button of the message box was click, logs the user out and goes back to
     * the log in screen
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
     * What to do if the no button of the message box was clicked
     *
     * @param message the message of the box that was utilized
     */
    @Override
    public void negativeButtonPressed(String message) {

    }
}