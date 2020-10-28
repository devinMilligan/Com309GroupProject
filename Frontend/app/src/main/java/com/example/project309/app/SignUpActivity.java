package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, ViewListenerInter {

    public static final String TAG = "SIGNUP";

    private MessageBoxInter message;

    private EditText email, password, firstName, lastName;
    private Button createAccountButton;
    private Switch deliveryStatus;

    private JSONHandlerInter jsonHandler;
    private StringHandlerInter stringHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        message = AppController.getInstance().getMessageBoxBuilderInstance();
        message.setContext(SignUpActivity.this);

        jsonHandler = AppController.getInstance().getJSONHandlerInstance();
        jsonHandler.setListener(this);

        stringHandler = AppController.getInstance().getStringHandlerInstance();
        stringHandler.setListener(this);

        email = (EditText) findViewById(R.id.signup_email);
        password = (EditText) findViewById(R.id.signup_password);
        firstName = (EditText) findViewById(R.id.signup_firstName);
        lastName = (EditText) findViewById(R.id.signup_lastName);
        deliveryStatus = (Switch) findViewById(R.id.account_type);

        createAccountButton = findViewById(R.id.signup_create_account_button);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch(v.getId()) {
            case R.id.signup_create_account_button:
                if(!email.getText().toString().equals("") && !password.getText().toString().equals("") && !firstName.getText().toString().equals("") && !lastName.getText().toString().equals("")) {
                    message.showMessage("Loading...", 3);

                    ArrayList<JSONVariable> list = new ArrayList<>();

                    list.add(new JSONVariable("email",email.getText().toString()));

                    stringHandler.makeStringParams(Const.URL_JSON_CHECK_EMAIL, list, RequestMethod.GET);
                }
                else {
                    Toast myToast;
                    if (email.getText().toString().equals("")) {
                        myToast = Toast.makeText(this, R.string.no_email, Toast.LENGTH_SHORT);
                        myToast.show();
                    }
                    else if (firstName.getText().toString().equals("")) {
                        myToast = Toast.makeText(this, R.string.no_firstName, Toast.LENGTH_SHORT);
                        myToast.show();
                    }
                    else if (lastName.getText().toString().equals("")) {
                        myToast = Toast.makeText(this, R.string.no_lastName, Toast.LENGTH_SHORT);
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


    @Override
    public void onSuccess(JSONObject response) {
        Log.d(TAG,response.toString());
        message.dismissMessage();

        Intent accountCreated = new Intent(this, LoginActivity.class);

        accountCreated.putExtra("EMAIL", email.getText().toString());
        accountCreated.putExtra("PASSWORD", password.getText().toString());

        startActivity(accountCreated);
    }

    @Override
    public void onSuccess(JSONArray response) {

    }

    @Override
    public void onSuccess(String response) {
        Log.d(TAG,response);
        message.dismissMessage();

        if (response.equalsIgnoreCase("true")) {
            message.showMessage("An account with that email already exists. Please enter a different email.", 1);
            return;
        }
        else if (response.equalsIgnoreCase("false")) {
            ArrayList<JSONVariable> list = new ArrayList<>();

            list.add(new JSONVariable("email", email.getText().toString()));
            list.add(new JSONVariable("password", password.getText().toString()));
            list.add(new JSONVariable("firstName", firstName.getText().toString()));
            list.add(new JSONVariable("lastName", lastName.getText().toString()));
            list.add(new JSONVariable("address", "n/a"));
            list.add(new JSONVariable("type", AccountType.CUSTOMER_DELIVERER_ACCOUNT.getAccountType()));
            list.add(new JSONVariable("imagePath", "n/a"));

            jsonHandler.makeJsonObjReqBody(Const.URL_JSON_CREATE_USER, list, RequestMethod.POST);
        }
    }

    @Override
    public void onError(VolleyError error) {

        Log.d(TAG,error.toString());
        message.dismissMessage();
        message.showMessage(error.toString(),1);
        error.printStackTrace();
    }


    /**
     * JSON Request Object, used for arrays, bodies, and parms

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
     Intent loggedIn = new Intent(LoginActivity.this, MainNavigationScreen.class);
     startActivity(loggedIn);
     return;
     }
     catch(JSONException e) {
     if(email.getText().toString().trim().equals("Admin")){
     Intent loggedIn = new Intent(LoginActivity.this, MainNavigationScreenAdmin.class);
     startActivity(loggedIn);
     return;
     }
     else if(email.getText().toString().trim().equals("Store")) {
     Intent loggedIn = new Intent(LoginActivity.this, MainNavigationScreenStore.class);
     startActivity(loggedIn);
     return;
     }
     s = " ";
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
     };*/
}