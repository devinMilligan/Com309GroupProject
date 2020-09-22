package com.example.project309;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = (EditText) findViewById(R.id.login_email);
        EditText password = (EditText) findViewById(R.id.login_password);

        Button loginButton = findViewById(R.id.login_continue_button);
        loginButton.setOnClickListener(this);

        Button forgotPasswordButton = findViewById(R.id.login_forgot_password_button);
        forgotPasswordButton.setOnClickListener(this);

        Button signUpButton = findViewById(R.id.login_create_account_button);
        signUpButton.setOnClickListener(this);

        Intent messageIntent = getIntent();
        if(!messageIntent.equals(null))
        {
            String emailText = messageIntent.getStringExtra("EMAIL");
            String passwordText = messageIntent.getStringExtra("PASSWORD");

            email.setText(emailText);
            password.setText(passwordText);
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.login_continue_button:
                EditText email = (EditText) findViewById(R.id.login_email);
                EditText password = (EditText) findViewById(R.id.login_password);
                if(!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    Intent loggedIn = new Intent(this, MainNavigationScreen.class);
                    startActivity(loggedIn);
                }
                else {
                    Toast myToast;
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
            case R.id.login_create_account_button:
                Intent createAccount = new Intent(this, SignUpActivity.class);
                startActivity(createAccount);
                break;
            case R.id.login_forgot_password_button:
                Toast myToast = Toast.makeText(this, R.string.forgot_password, Toast.LENGTH_SHORT);
                myToast.show();
                break;
        }

    }
}