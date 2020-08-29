package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private TextView signIn, txtRePass, txtPass, txtReEmail;
    private Button btnSignUp;

    private EditText edEmail, edPassword, edRePass, edAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtPass = (TextView)findViewById(R.id.txtRePass);
        txtReEmail = (TextView)findViewById(R.id.txtReEm);
        txtRePass = (TextView)findViewById(R.id.txtReRePass);
        signIn = (TextView)findViewById(R.id.txtSignIn);
        signIn.setOnClickListener(this);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        edAddress = (EditText)findViewById(R.id.edAddress);
        edEmail = (EditText)findViewById(R.id.edEmailSign);
        edPassword = (EditText)findViewById(R.id.edPassSign);
        edRePass = (EditText)findViewById(R.id.edPass2Sign);
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

                boolean check = false;

                String rePass = edRePass.getText().toString().trim();
                String pass = edPassword.getText().toString().trim();
                String email = edEmail.getText().toString().trim();
                String add = edAddress.getText().toString();
                if(add.isEmpty()){

                }
                if(rePass.isEmpty()){

                    txtRePass.setTextColor(Color.RED);
                    check = true;
                }else{
                    txtRePass.setTextColor(getResources().getColor(R.color.AccentText));
                }
                if(pass.isEmpty()){

                    txtPass.setTextColor(Color.RED);
                    check = true;
                }else {
                    txtPass.setTextColor(getResources().getColor(R.color.AccentText));
                }
                if(!check && !rePass.equals(pass)){

                    txtRePass.setTextColor(Color.RED);
                    check = true;
                }
                else if(!check){
                    txtRePass.setTextColor(getResources().getColor(R.color.AccentText));
                    txtPass.setTextColor(getResources().getColor(R.color.AccentText));
                }

                if(email.isEmpty()){

                    txtReEmail.setTextColor(Color.RED);
                    check = true;
                }else{
                    txtReEmail.setTextColor(getResources().getColor(R.color.AccentText));
                }

                if(check){
                    break;
                }

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
