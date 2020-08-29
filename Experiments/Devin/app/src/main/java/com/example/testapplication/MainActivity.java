package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signUp;
    private EditText edEmail, edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = (TextView)findViewById(R.id.txtSignUp);
        signUp.setOnClickListener(this);

        edEmail = (EditText)findViewById(R.id.edEmail);
        edPass = (EditText)findViewById(R.id.edPass);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.txtSignUp:

                String email = edEmail.getText().toString().trim();
                String pass = edPass.getText().toString().trim();

                if(email.isEmpty()){
                    break;
                }
                if(pass.isEmpty()){
                    break;
                }

                Intent startSignUpIntent = new Intent(this, SignUp.class);
                startActivity(startSignUpIntent);
                finish();

                break;

        }

    }
}
