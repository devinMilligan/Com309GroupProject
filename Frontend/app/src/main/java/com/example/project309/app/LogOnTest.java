package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.project309.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class LogOnTest extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "LOGON";

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
        //jsonRe = new JsonRequestSpec(LogOnTest.this);
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


                if(checkIfComplete(tempUser, tempPass)){
                    message.showMessage("Loading...", 3);


                    ArrayList<JSONVariable> list = new ArrayList<>();
                    list.add(new JSONVariable("user",tempUser));
                    list.add(new JSONVariable("pass", tempPass));
                    stringRe.makeStringReq(1);
                    //jsonRe.makeJsonReqBody(list);
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
    public void sendResponseString(String txt){

        Log.d(TAG,txt);
        message.dismissMessage();
        message.showMessage(txt,1);

    }

    public void sendResponseJSON(String txt){


        Log.d(TAG,txt);
        message.dismissMessage();
        message.showMessage(txt,1);
    }


    JsonRequestSpec jsonRe = new JsonRequestSpec(LogOnTest.this){

        @Override
        protected void responseListBody(JSONObject response){
            Log.d(TAG,response.toString());
            message.dismissMessage();
            message.showMessage(response.toString(),1);
        }

    };

    StringRequestSpec stringRe = new StringRequestSpec(LogOnTest.this){

        @Override
        protected void responseListString(String response){

            Log.d(TAG,response.toString());
            message.dismissMessage();
            message.showMessage(response.toString(),1);

        }

        @Override
        protected void responseListString(VolleyError error){

            Log.d(TAG,error.toString());
            message.dismissMessage();
            message.showMessage(error.toString(),1);

        }

    };

}
