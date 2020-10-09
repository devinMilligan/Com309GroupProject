package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project309.R;

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
                    if(email.getText().toString().trim().equals("Admin")){
                        Intent loggedIn = new Intent(this, MainNavigationScreenAdmin.class);
                        startActivity(loggedIn);

                    }else if(email.getText().toString().trim().equals("Store")) {
                        Intent loggedIn = new Intent(this, MainNavigationScreenStore.class);
                        startActivity(loggedIn);

                    }else {
                        Intent loggedIn = new Intent(this, MainNavigationScreen.class);
                        startActivity(loggedIn);

                    }
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

    /**
     * JSON Request Object, used for arrays, bodies, and parms
     */
    JsonRequestSpec jsonRe = new JsonRequestSpec(TAG){

        //Overridden methods to specify how to handle responses and errors
        @Override
        protected void responseListArray(JSONArray response){
            Log.d(TAG,response.toString());
            message.dismissMessage();

            String s;
            for(int i = 0; i < response.length(); i++) {
                try {
                    s = response.get(i).toString();
                    if(email.getText().toString().trim().equals("Admin")){
                        Intent loggedIn = new Intent(this, MainNavigationScreenAdmin.class);
                        startActivity(loggedIn);

                    }else if(email.getText().toString().trim().equals("Store")) {
                        Intent loggedIn = new Intent(this, MainNavigationScreenStore.class);
                        startActivity(loggedIn);

                    }else {
                        Intent loggedIn = new Intent(this, MainNavigationScreen.class);
                        startActivity(loggedIn);

                    }
                    return;
                }
                catch(JSONException e) {
                    s = null;
                }
            }
            message.showMessage("Login Failed", 1);
        }

        @Override
        protected void responseListArray(VolleyError error){
            Log.d(TAG,error.toString());
            message.dismissMessage();
            message.showMessage(error.toString(),1);
        }
    };
}