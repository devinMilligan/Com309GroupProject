package com.example.project309.app.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.app.AppController;
import com.example.project309.app.CustomerDelivererProfile;
import com.example.project309.app.JSONHandler;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.JSONRequestInter;
import com.example.project309.app.JSONVariable;
import com.example.project309.app.MessageBoxInter;
import com.example.project309.app.MessageBoxListenerInter;
import com.example.project309.app.Profile;
import com.example.project309.app.RequestMethod;
import com.example.project309.app.StringHandlerInter;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * This class is used to show and update the values of the user's account and object
 *
 * @author Devin Milligan
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, ViewListenerInter, MessageBoxListenerInter {

    /**
     * ProfileViewModel instance used to get the current user account information
     */
    private ProfileViewModel profileViewModel;

    /**
     * Buttons on the fragment
     */
    private Button changePass, btnSave;

    /**
     * Keeps track of the status of email availability
     */
    private int emailAvailable = 1;

    /**
     * EditTexts on the fragment
     */
    private EditText edFirstN, edLastN, edEmail, edPass, edPassRe, edAddress;
    /**
     * TextViews on the fragment
     */
    private TextView txtPass, txtPassRe, txtEmail;

    /**
     * {@link com.example.project309.app.MessageBoxBuilder} Instance used to display the messages to the user
     */
    private MessageBoxInter message;

    /**
     * {@link CustomerDelivererProfile} Instance that is the current logged in person who is viewing their info
     */
    private CustomerDelivererProfile prof;

    /**
     * {@link JSONHandler} instance used to create requests to the server
     */
    private JSONHandlerInter jsonH;

    /**
     * {@link com.example.project309.app.StringHandler} instance used to create string requests to the server
     */
    private StringHandlerInter stringH;

    /**
     * This is ran when the fragment is created and opened, sets the initial values to the on screen
     * fields and initializes all the variables
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

        stringH = AppController.getInstance().getStringHandlerInstance();
        stringH.setListener(this);

        message = AppController.getInstance().getMessageBoxBuilderInstance();
        message.setContext(root.getContext());
        message.setListener(this);

        final TextView textView = root.findViewById(R.id.text_profile);

        changePass =(Button)root.findViewById(R.id.btnChangePass);
        changePass.setOnClickListener(this);
        btnSave = (Button)root.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        edEmail = (EditText)root.findViewById(R.id.editEmail);
        edEmail.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus && emailAvailable != 0){
                    ArrayList<JSONVariable> paramList = new ArrayList<>();
                    paramList.add(new JSONVariable("email", edEmail.getText().toString().trim()));
                    stringH.makeStringParams(Const.URL_STRING_CHECK_EMAIL,paramList, RequestMethod.GET);
                    emailAvailable = 0;
                    v.setEnabled(false);
                }
            }
        });

        edAddress = (EditText)root.findViewById(R.id.editAddress);
        edFirstN = (EditText)root.findViewById(R.id.editFirst);
        edLastN = (EditText)root.findViewById(R.id.editLast);
        edPass = (EditText)root.findViewById(R.id.editPassword) ;
        edPassRe = (EditText)root.findViewById(R.id.editPasswordRe) ;

        txtPass = (TextView)root.findViewById(R.id.txtPass);
        txtPassRe = (TextView)root.findViewById(R.id.txtPassRe);

        txtEmail = (TextView)root.findViewById(R.id.txtEmail);

        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        profileViewModel.getCurrentProfile().observe(this, new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {

                if(profile != null) {

                    edEmail.setText(((CustomerDelivererProfile)profile).getEmail());
                    edFirstN.setText(((CustomerDelivererProfile)profile).getFirstName());
                    edLastN.setText(((CustomerDelivererProfile)profile).getLastName());
                    edPass.setText(((CustomerDelivererProfile)profile).getPassword());
                    edAddress.setText(((CustomerDelivererProfile)profile).getAddress());
                    edPassRe.setText("");

                    prof = ((CustomerDelivererProfile)profile);

                }

            }
        });

        return root;
    }

    /**
     * Recieves all the buttons clicks and determines what to do based on the button
     *
     * @param v the view of the button that was clicked
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnChangePass:

                boolean complete = true;

                String passFirst = edPass.getText().toString().trim();
                String passSecond = edPassRe.getText().toString().trim();
                if(edPass == null || edPass.equals("")){
                    txtPass.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    complete = false;
                }else{
                    txtPass.setTextColor(getResources().getColor(R.color.colorTextSecond));
                }
                if(edPassRe == null || edPassRe.equals("")){
                    txtPassRe.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    complete = false;
                }else{
                    txtPassRe.setTextColor(getResources().getColor(R.color.colorTextSecond));
                }

                if(complete){

                    ArrayList<JSONVariable> paramsList = new ArrayList<>();
                    paramsList.add(new JSONVariable("id", Integer.toString(prof.getId())));
                    paramsList.add(new JSONVariable("newPassword",passFirst));
                    jsonH.makeJsonObjReqParams(Const.URL_JSON_UPDATE_USER_PASSWORD,paramsList, RequestMethod.POST);
                    message.showMessage("Updating...",3);


                }else{
                    message.showMessage("Please fill the required fields", 1);
                }


                break;

            case R.id.btnSave:

                while(emailAvailable == 0);
                if(emailAvailable == 1) {
                    message.showMessage("Save Changes?", 2);
                }else{
                    message.showMessage("Email is not available", 1);
                }

                break;


        }

    }

    /**
     * Receives the JSONObject response from the server of the user object and updates the fields on the screen
     * and the current user object
     *
     * @param response the current user that was just updated in JSONObject form
     */
    @Override
    public void onSuccess(JSONObject response) {

        CustomerDelivererProfile temp;

        //This will resturn a Profile object from response
        temp = CustomerDelivererProfile.getProfileInfo(response);
        prof = temp;
        message.dismissMessage();

        if(temp.getPassword().equals(edPass.getText().toString().trim())){
            message.showMessage("Updated!", 1);
        }else{
            message.showMessage("An Error Occurred", 1);
        }


        if(prof != null) {
            edEmail.setText(prof.getEmail());
            edFirstN.setText(prof.getFirstName());
            edLastN.setText(prof.getLastName());
            edPass.setText(prof.getPassword());
            edAddress.setText(prof.getAddress());
            edPassRe.setText("");
        }




    }

    /**
     * If the server returns a JSON Array with the user contatined in it this will send it to the correct
     * method handler to be sure to update the fields
     *
     * @param response JSONArray contatining the user JSONObject
     */
    @Override
    public void onSuccess(JSONArray response){

        try {
            JSONObject temp = (JSONObject) response.getJSONObject(0);
            onSuccess(temp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Receives the servers resoponse of the string determining if the user's email is valid
     *
     * @param response string saying the the user's email is valid
     */
    @Override
    public void onSuccess(String response) {

        edEmail.setEnabled(true);
        if(response.equals("true")){
            emailAvailable = 1;
            txtPass.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        else{
            emailAvailable = 2;
            txtPass.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    /**
     * Handles an error if the server request goes bad
     *
     * @param error the error that was thrown
     */
    @Override
    public void onError(VolleyError error) {

        message.dismissMessage();
        message.showMessage("Request Error: " + error.toString(), 1);

    }

    /**
     * What to do if the user dismissed the message box
     *
     * @param message the message that was dismissed
     */
    @Override
    public void onDismiss(String message) {

    }

    /**
     * What to do if the user pressed the ok button on the message box
     *
     * @param message the message on the box that was utilized
     */
    @Override
    public void neutralButtonPressed(String message) {

    }

    /**
     * What to do if the user pressed the yes button on the message box, it will make a request to udpate
     * the user in the server
     *
     * @param message the message of the box that was utilized
     */
    @Override
    public void positiveButtonPressed(String message) {

        if(prof != null) {
            ArrayList<JSONVariable> paramsList = new ArrayList<>();
            paramsList.add(new JSONVariable("id", Integer.toString(prof.getId())));
            paramsList.add(new JSONVariable("email", edEmail.getText().toString().trim()));
            paramsList.add(new JSONVariable("firstname", edFirstN.getText().toString().trim()));
            paramsList.add(new JSONVariable("lastname", edLastN.getText().toString().trim()));
            paramsList.add(new JSONVariable("password", edPass.getText().toString().trim()));
            paramsList.add(new JSONVariable("address", edAddress.getText().toString().trim()));
            paramsList.add(new JSONVariable("type",prof.getAccountType().getAccountType()));
            paramsList.add(new JSONVariable("image", "na"));
            jsonH.makeJsonObjReqParams(Const.URL_JSON_UPDATE_USER, paramsList, RequestMethod.POST);
            this.message.showMessage("Updating...",3);
        }

    }

    /**
     * What to do if the no button is pressed on the message box
     *
     * @param message the message of the box that was utilized
     */
    @Override
    public void negativeButtonPressed(String message) {

        if(prof != null) {
            edEmail.setText(prof.getEmail());
            edFirstN.setText(prof.getFirstName());
            edLastN.setText(prof.getLastName());
            edPass.setText(prof.getPassword());
            edAddress.setText(prof.getAddress());
            edPassRe.setText("");
        }

    }
}