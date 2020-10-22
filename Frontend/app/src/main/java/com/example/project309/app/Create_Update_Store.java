package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Create_Update_Store extends AppCompatActivity implements View.OnClickListener, ViewListenerInter {

    EditText edStoreName, edAddress, edManager, edSundayOpen, edSundayClose, edMondayOpen, edMondayClose,
        edTuesdayOpen, edTuesdayClose, edWednesdayOpen, edWednesdayClose, edThursdayOpen, edThursdayClose,
        edFridayOpen, edFridayClose, edSaturdayOpen, edSaturdayClose;

    TextView txtStoreName, txtAddress, txtManager, txtSundayOpen, txtSundayClose, txtMondayOpen, txtMondayClose,
            txtTuesdayOpen, txtTuesdayClose, txtWednesdayOpen, txtWednesdayClose, txtThursdayOpen, txtThursdayClose,
            txtFridayOpen, txtFridayClose, txtSaturdayOpen, txtSaturdayClose;

    Button btnSave;

    JSONHandlerInter jsonH;

    String createUpdateUser;

    MessageBoxInter message;

    double longi, lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_update_store);

        createUpdateUser = getIntent().getStringExtra("Create/Update");

        message = AppController.getInstance().getMessageBoxBuilderInstance();
        message.setContext(Create_Update_Store.this);

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

        btnSave = findViewById(R.id.btnSaveStoreProf);
        btnSave.setOnClickListener(this);

        edStoreName = findViewById(R.id.editStoreName);
        edAddress = findViewById(R.id.editAddress);
        edManager = findViewById(R.id.editManager);
        edSundayOpen = findViewById(R.id.editSundayOpen);
        edSundayClose = findViewById(R.id.editSundayClose);
        edMondayOpen = findViewById(R.id.editMondayOpen);
        edMondayClose = findViewById(R.id.editMondayClose);
        edTuesdayOpen = findViewById(R.id.editTuesdayOpen);
        edTuesdayClose = findViewById(R.id.editTuesdayClose);
        edWednesdayOpen = findViewById(R.id.editWednesdayOpen);
        edWednesdayClose = findViewById(R.id.editWednesdayClose);
        edThursdayOpen = findViewById(R.id.editThursdayOpen);
        edThursdayClose = findViewById(R.id.editThursdayClose);
        edFridayOpen = findViewById(R.id.editFridayOpen);
        edFridayClose = findViewById(R.id.editFridayClose);
        edSaturdayOpen = findViewById(R.id.editSaturdayOpen);
        edSaturdayClose = findViewById(R.id.editSaturdayClose);

        txtStoreName = findViewById(R.id.txtStoreName);
        txtAddress = findViewById(R.id.txtAddress);
        txtManager = findViewById(R.id.txtManager);
        txtSundayOpen = findViewById(R.id.txtSundayOpen);
        txtSundayClose = findViewById(R.id.txtSundayClose);
        txtMondayOpen = findViewById(R.id.txtMondayOpen);
        txtMondayClose = findViewById(R.id.txtMondayClose);
        txtTuesdayOpen = findViewById(R.id.txtTuesdayOpen);
        txtTuesdayClose = findViewById(R.id.txtTuesdayClose);
        txtWednesdayOpen = findViewById(R.id.txtWednesdayOpen);
        txtWednesdayClose = findViewById(R.id.txtWednesdayClose);
        txtThursdayOpen = findViewById(R.id.txtThursdayOpen);
        txtThursdayClose = findViewById(R.id.txtThursdayClose);
        txtFridayOpen = findViewById(R.id.txtFridayOpen);
        txtFridayClose = findViewById(R.id.txtFridayClose);
        txtSaturdayOpen = findViewById(R.id.txtSaturdayOpen);
        txtSaturdayClose = findViewById(R.id.txtSaturdayClose);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnSaveStoreProf:

                if(!areFieldsEmpty()) {

                    ArrayList<JSONVariable> bodyList = new ArrayList<>();
                    bodyList.add(new JSONVariable("name", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("address", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("manager", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("latitude", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("longitude", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_sunday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_sunday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_monday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_monday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_tuesday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_tuesday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_wednesday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_wednesday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_thursday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_thursday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_friday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_friday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_saturday", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_saturday", edStoreName.getText().toString().trim()));


                    if (createUpdateUser.equals("Create")) {
                        jsonH.makeJsonObjReqBody(Const.URL_JSON_ADD_STORE, bodyList, RequestMethod.POST);
                        message.showMessage("Creating...", 3);
                    } else {
                        jsonH.makeJsonObjReqBody(Const.URL_JSON_UPDATE_STORE, bodyList, RequestMethod.POST);
                        message.showMessage("Updating...", 3);
                    }

                }else{
                    message.showMessage("Fill in the required fields", 1);
                }


                break;

        }

    }

    @Override
    public void onSuccess(JSONObject response) {

        message.dismissMessage();
        finish();

    }

    @Override
    public void onSuccess(JSONArray response) {

    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

        message.dismissMessage();
        message.showMessage("Error: " + error.toString(),1);

    }

    private boolean areFieldsEmpty(){

        boolean check = true;

        if(edAddress.getText().toString().trim().isEmpty()){
            check = false;
            edAddress.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edAddress.setTextColor(getResources().getColor(R.color.colorTextSecond));
            if(!getLocationFromAddress(edAddress.getText().toString().trim())){
                check = false;
                edAddress.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        }
        if(edManager.getText().toString().trim().isEmpty()){
            check = false;
            edManager.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edManager.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edStoreName.getText().toString().trim().isEmpty()){
            check = false;
            edStoreName.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edStoreName.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edSundayClose.getText().toString().trim().isEmpty()){
            check = false;
            edSundayClose.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edSundayClose.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edSundayOpen.getText().toString().trim().isEmpty()){
            check = false;
            edSundayOpen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edSundayOpen.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edMondayClose.getText().toString().trim().isEmpty()){
            check = false;
            edMondayClose.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edAddress.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edMondayOpen.getText().toString().trim().isEmpty()){
            check = false;
            edMondayOpen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edAddress.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edTuesdayClose.getText().toString().trim().isEmpty()){
            check = false;
            edTuesdayClose.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edTuesdayClose.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edTuesdayOpen.getText().toString().trim().isEmpty()){
            check = false;
            edTuesdayOpen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edTuesdayOpen.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edWednesdayClose.getText().toString().trim().isEmpty()){
            check = false;
            edWednesdayClose.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edWednesdayClose.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edWednesdayOpen.getText().toString().trim().isEmpty()){
            check = false;
            edWednesdayOpen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edWednesdayOpen.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edThursdayClose.getText().toString().trim().isEmpty()){
            check = false;
            edThursdayClose.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edThursdayClose.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edThursdayOpen.getText().toString().trim().isEmpty()){
            check = false;
            edThursdayOpen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edThursdayOpen.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edFridayClose.getText().toString().trim().isEmpty()){
            check = false;
            edFridayClose.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edFridayClose.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edFridayOpen.getText().toString().trim().isEmpty()){
            check = false;
            edFridayOpen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edFridayOpen.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edSaturdayClose.getText().toString().trim().isEmpty()){
            check = false;
            edSaturdayClose.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edSaturdayClose.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edSaturdayOpen.getText().toString().trim().isEmpty()){
            check = false;
            edSaturdayOpen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edSaturdayOpen.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }

        return check;

    }

    public boolean getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;


        try {
            address = coder.getFromLocationName(strAddress,5);

            if (address==null) {
                return false;
            }
            Address location=address.get(0);
            longi = location.getLatitude();
            lat = location.getLongitude();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

}