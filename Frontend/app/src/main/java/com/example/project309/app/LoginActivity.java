package com.example.project309.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ViewListenerInter{

    public static final String TAG = "LOGON";

    public int success;

    private MessageBoxInter message;

    private EditText email, password;
    private Button loginButton, forgotPasswordButton, signUpButton;

    private String emailText, passwordText;

    private JSONHandlerInter jsonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Store.allStores.clear();
        ManagerProfile.managers.clear();

        message = AppController.getInstance().getMessageBoxBuilderInstance();
        message.setContext(LoginActivity.this);

        jsonHandler = AppController.getInstance().getJSONHandlerInstance();
        jsonHandler.setListener(this);

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
    public void onResume() {
        super.onResume();

        Store.allStores.clear();
        ManagerProfile.managers.clear();

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

                    jsonHandler.makeJsonObjReqParams(Const.URL_JSON_LOGIN, list, RequestMethod.GET);
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

    @Override
    public void onSuccess(JSONObject response) {
        Log.d(TAG,response.toString());
        message.dismissMessage();

        success = 1;

        Intent loggedIn;

            try {
                if(response.get("type").toString().equalsIgnoreCase(AccountType.ADMIN_ACCOUNT.getAccountType())) {
                    Profile.currentLogin = Profile.getProfileInfo(response);
                    loggedIn = new Intent(LoginActivity.this, MainNavigationScreenAdmin.class);
                    startActivity(loggedIn);
                    return;
                }
                else if(response.get("type").toString().equalsIgnoreCase(AccountType.CUSTOMER_DELIVERER_ACCOUNT.getAccountType()))
                {
                    Profile.currentLogin = CustomerDelivererProfile.getProfileInfo(response);
                    loggedIn = new Intent(LoginActivity.this, MainNavigationScreen.class);
                    startActivity(loggedIn);
                    return;
                }
                else if(response.get("type").toString().equalsIgnoreCase(AccountType.MANAGER_ACCOUNT.getAccountType()))
                {
                    Profile.currentLogin = Profile.getProfileInfo(response);
                    loggedIn = new Intent(LoginActivity.this, ManagerPickStore.class);
                    startActivity(loggedIn);
                    return;
                }
                else {
                    if(email.getText().toString().trim().equals("Admin")){
                        loggedIn = new Intent(LoginActivity.this, MainNavigationScreenAdmin.class);
                        startActivity(loggedIn);
                        return;
                    }
                    else if(email.getText().toString().trim().equals("Store")) {
                        loggedIn = new Intent(LoginActivity.this, MainNavigationScreenStore.class);
                        startActivity(loggedIn);
                        return;
                    }
                }
            }
            catch(JSONException e) {
                return;
            }
        message.showMessage("Login Failed", 1);
            success = 0;
    }

    @Override
    public void onSuccess(JSONArray response) {

    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

        Log.d(TAG,error.toString());
        message.dismissMessage();

        success = 0;

        if(error.toString().contains("End of input")) {
            message.showMessage("User does not exist", 1);
        }
        else {
            message.showMessage(error.toString(),1);
        }

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

