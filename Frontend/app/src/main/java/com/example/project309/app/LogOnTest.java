package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LogOnTest extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "LOGON";

    private MessageBoxBuilder message;

    private TextView tvUser;

    private EditText edUser, edPass, edAdd, edFirst, edLast;
    private Button btnLogOn, btnSignUp, btnNext;

    private String user = "";
    private String pass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on_test);

        btnLogOn = (Button)findViewById(R.id.btnLogOn);
        btnSignUp = (Button)findViewById(R.id.btnCreateUser);
        btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnLogOn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        edPass = (EditText)findViewById(R.id.edPass);
        edUser = (EditText)findViewById(R.id.edUser);
        edAdd = (EditText)findViewById(R.id.edAddress);
        edFirst = (EditText)findViewById(R.id.edFirstName);
        edLast = (EditText)findViewById(R.id.edLast);

        tvUser = (TextView)findViewById(R.id.txtUsers);

        edPass.setText("");
        edUser.setText("");

        message = new MessageBoxBuilder(LogOnTest.this);

    }


    @Override
    public void onClick(View v) {

        String tempUser = "";
        String tempPass = "";

        switch (v.getId()){

            //Button to go to rest of app
            case R.id.btnNext:

                Intent in = new Intent(this, LoginActivity.class);
                startActivity(in);

                break;

                //Button to send a request with new user data to be created
            case R.id.btnCreateUser:

                tempUser = edUser.getText().toString().trim();
                tempPass = edPass.getText().toString().trim();


                if(checkIfComplete(tempUser, tempPass)){
                    message.showMessage("Loading...", 3);

                    ArrayList<JSONVariable> list = new ArrayList<>();

                    list.add(new JSONVariable("username",edFirst.getText().toString()));
                    list.add(new JSONVariable("password", edPass.getText().toString()));
                    list.add(new JSONVariable("firstname",edFirst.getText().toString()));
                    list.add(new JSONVariable("lastname",edLast.getText().toString()));
                    list.add(new JSONVariable("address",edAdd.getText().toString()));

                    jsonRe.makeJsonReqBody(Const.URL_JSON_CREATE_USER,null,list);
                }
                else{
                    message.showMessage("Please fill out user and password",1);
                }

                break;

                //button to update and show all the users in the database
            case R.id.btnLogOn:

                message.showMessage("Updating...", 3);

                jsonRe.makeJsonArryReq(Const.URL_JSON_ARRAY_ALL_USERS);

                break;

        }

    }

    /**
     * Checks if the username and password fields have been filled out
     *
     * @param user
     * @param pass
     * @return
     */
    private boolean checkIfComplete(String user, String pass){


        if(user.isEmpty() || user == "" || pass.isEmpty() || pass == ""){
            return false;
        }
        return true;

    }

    /**
     * JSON Request Object, used for arrays, bodies, and parms
     */
    JsonRequestSpec jsonRe = new JsonRequestSpec(TAG){

        //Overridden methods to specify how to handle responses and errors
        @Override
        protected void responseListBody(JSONObject response){
            Log.d(TAG,response.toString());
            message.dismissMessage();
            message.showMessage(response.toString(),1);
        }

        @Override
        protected void responseListBody(VolleyError error){
            Log.d(TAG,error.toString());
            message.dismissMessage();
            message.showMessage(error.toString(),1);
        }

        @Override
        protected void responseListParam(JSONObject response) {
            Log.d(TAG,response.toString());
            message.dismissMessage();
            message.showMessage(response.toString(),1);
        }

        @Override
        protected void responseListParam(VolleyError error) {
            Log.d(TAG,error.toString());
            message.dismissMessage();
            message.showMessage(error.toString(),1);
        }

        @Override
        protected void responseListArray(JSONArray response) {
            Log.d(TAG,response.toString());

            try {
                for (int i = 0; i < response.length(); i++) {   //Add all new profiles to the app Profile class and then display in text box

                    JSONObject temp = (JSONObject)response.get(i);

                    Profile prof = new Profile(((Integer)temp.get("id")).intValue());
                    prof.setUserName(temp.get("username").toString());
                    prof.setPassword(temp.get("password").toString());
                    prof.setAccountType(((Integer)temp.get("accountType")).intValue());

                }
            }catch(JSONException e){
                Log.d(TAG, "Not Ideal");
            }

            message.dismissMessage();

            String temp = "";

            for(int i = 0; i<Profile.allProfiles.size(); i++){

                temp+= Profile.allProfiles.get(i).getName() + ", ";

            }

            tvUser.setText(temp);

        }

        @Override
        protected void responseListArray(VolleyError error) {
            Log.d(TAG,error.toString());
            message.dismissMessage();
            message.showMessage(error.toString(),1);
        }
    };

    /**
     * String request, used to request a string from the database based off a certain URL
     */
    StringRequestSpec stringRe = new StringRequestSpec(TAG){

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
