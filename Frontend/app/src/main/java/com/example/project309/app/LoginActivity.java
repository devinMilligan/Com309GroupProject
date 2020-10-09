package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "LOGON";

    private MessageBoxBuilder message;

    private EditText email, password;
    private Button loginButton, forgotPasswordButton, signUpButton;

    private String emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        message = new MessageBoxBuilder(LoginActivity.this);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);

        loginButton = findViewById(R.id.login_continue_button);
        loginButton.setOnClickListener(this);

        forgotPasswordButton = findViewById(R.id.login_forgot_password_button);
        forgotPasswordButton.setOnClickListener(this);

        signUpButton = findViewById(R.id.login_create_account_button);
        signUpButton.setOnClickListener(this);

        Intent messageIntent = getIntent();
        if(!messageIntent.equals(null))
        {
            emailText = messageIntent.getStringExtra("EMAIL");
            passwordText = messageIntent.getStringExtra("PASSWORD");

            email.setText(emailText);
            password.setText(passwordText);
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.login_continue_button:
                if(!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
                    message.showMessage("Loading...", 3);

                    ArrayList<JSONVariable> list = new ArrayList<>();

                    list.add(new JSONVariable("email",email.getText().toString()));
                    list.add(new JSONVariable("password", password.getText().toString()));

                    jsonRe.makeJsonArryReq(Const.URL_JSON_LOGIN, list);
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