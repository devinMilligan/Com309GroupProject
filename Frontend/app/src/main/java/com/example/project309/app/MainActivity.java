package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project309.R;

/**
 * Starting point for the app that directs users to their first screen
 *
 * @author Ryan Hickok
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Sets up the activity and begins the app on the login screen
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in); //moving

    }
}
