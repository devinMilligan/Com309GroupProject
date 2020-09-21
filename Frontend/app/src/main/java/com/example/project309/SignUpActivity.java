package com.example.project309;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button createAccountButton = findViewById(R.id.signup_create_account_button);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.signup_create_account_button:
                EditText email = (EditText) findViewById(R.id.signup_email);
                EditText password = (EditText) findViewById(R.id.signup_password);
                Toast myToast;
                if(!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    Intent accountCreated = new Intent(this, MainNavigationScreen.class);
                    startActivity(accountCreated);
                }
                else {
                    if (email.getText().toString().equals("")) {
                        myToast = Toast.makeText(this, R.string.no_email, Toast.LENGTH_SHORT);
                        myToast.show();
                    }
                    else {
                        myToast = Toast.makeText(this, R.string.no_password, Toast.LENGTH_SHORT);
                        myToast.show();
                    }
                }
                break;
        }
    }
}