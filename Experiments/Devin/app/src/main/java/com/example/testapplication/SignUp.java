package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private TextView signIn;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signIn = (TextView)findViewById(R.id.txtSignIn);
        signIn.setOnClickListener(this);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.txtSignIn:

                Intent cancelSignUpIntent = new Intent(this, MainActivity.class);
                startActivity(cancelSignUpIntent);
                finish();

                break;

            case R.id.btnSignUp:

                Intent signUpIntent = new Intent(this, MainActivity.class);
                startActivity(signUpIntent);
                finish();

                break;


        }
    }

    @Override
    public void onBackPressed(){

        Intent cancelSignUpIntent = new Intent(this, MainActivity.class);
        startActivity(cancelSignUpIntent);
        finish();

    }

}
