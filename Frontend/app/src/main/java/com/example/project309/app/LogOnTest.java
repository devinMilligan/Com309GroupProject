package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project309.R;

public class LogOnTest extends AppCompatActivity implements View.OnClickListener {

    private StringRequestSpec stringRe;

    private MessageBoxBuilder message;

    private EditText edUser, edPass;
    private Button btnLogOn, btnSignUp;

    private String user = "";
    private String pass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on_test);

        btnLogOn = (Button)findViewById(R.id.btnLogOn);
        btnSignUp = (Button)findViewById(R.id.btnCreateUser);
        btnLogOn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        edPass = (EditText)findViewById(R.id.edPass);
        edUser = (EditText)findViewById(R.id.edUser);

        edPass.setText("");
        edUser.setText("");

        stringRe = new StringRequestSpec(LogOnTest.this);
        message = new MessageBoxBuilder(LogOnTest.this);

    }


    @Override
    public void onClick(View v) {

        String tempUser = "";
        String tempPass = "";

        switch (v.getId()){

            case R.id.btnCreateUser:

                tempUser = edUser.getText().toString().trim();
                tempPass = edPass.getText().toString().trim();

                stringRe.makeStringReq();

                if(checkIfComplete(tempUser, tempPass)){
                    message.showMessage("Sending Request To Sign On", 1);
                }
                else{
                    message.showMessage("Please fill out user and password",1);
                }


                break;

            case R.id.btnLogOn:

                tempUser = edUser.getText().toString().trim();
                tempPass = edPass.getText().toString().trim();

                if(checkIfComplete(tempUser, tempPass)){
                    message.showMessage("Sending Request to Create User", 1);
                }
                else{
                    message.showMessage("Please fill out user and password",1);
                }


                break;

        }

    }

    private boolean checkIfComplete(String user, String pass){


        if(user.isEmpty() || user == "" || pass.isEmpty() || pass == ""){
            return false;
        }
        return true;

    }
}
