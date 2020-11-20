package com.example.project309.app;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.app.uiStore.profile.ProfileFragmentStore;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.project309.app.Store.getLocationFromAddress;

/**
 * This class allows for the updating and creation of a store on the server through this activity that
 * displays all the fields of the store
 *
 * @author Devin Milligan
 */
public class Create_Update_Store extends AppCompatActivity implements View.OnClickListener, ViewListenerInter {

    /**
     * EditTexts on the activity
     */
    EditText edStoreName, edAddress, edSundayOpen, edSundayClose, edMondayOpen, edMondayClose,
        edTuesdayOpen, edTuesdayClose, edWednesdayOpen, edWednesdayClose, edThursdayOpen, edThursdayClose,
        edFridayOpen, edFridayClose, edSaturdayOpen, edSaturdayClose;

    /**
     * TextView of the managers to be autocompleted during text
     */
    MultiAutoCompleteTextView edManager;

    /**
     * TextViews on the Activity
     */
    TextView txtStoreName, txtAddress, txtManager, txtSundayOpen, txtSundayClose, txtMondayOpen, txtMondayClose,
            txtTuesdayOpen, txtTuesdayClose, txtWednesdayOpen, txtWednesdayClose, txtThursdayOpen, txtThursdayClose,
            txtFridayOpen, txtFridayClose, txtSaturdayOpen, txtSaturdayClose;

    /**
     * Button used to save edits
     */
    Button btnSave;

    /**
     * {@link JSONHandler} instance used to make requests to the server
     */
    JSONHandlerInter jsonH;

    /**
     * String that used to determine if the user is creating or updating a store
     */
    String createUpdateUser;

    /**
     * {@link MessageBoxBuilder} instance used to display messages to the user
     */
    MessageBoxInter message;

    /**
     * {@link PointLocation} instance to store the coordinates of the store
     */
    PointLocation pointLocation;

    /**
     * ArrayList of {@link ManagerProfile} instances used to autocomplete the field
     */
    ArrayList<ManagerProfile> managers;
    /**
     * {@link ManagerListAdapter} instance used to help display the instance on the view
     */
    ManagerListAdapter managerListAdapter;
    /**
     * This instance holds the new Managers if the manaeger was edited
     */
    ManagerProfile newManager;
    /**
     * instance to store the current Manager of the store
     */
    ManagerProfile currentManager;


    /**
     * Runs on the creation of the Activity and initializes all thes fields to the current value of
     * the fields contatined in the current store
     *
     * @param savedInstanceState
     */
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
        edManager = (MultiAutoCompleteTextView) findViewById(R.id.editManager);
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

        if(createUpdateUser.equals("Update")) {
            edSaturdayClose.setText(Store.currentStore.getSaturdayClose());
            edSaturdayOpen.setText(Store.currentStore.getSaturdayOpen());
            edSundayOpen.setText(Store.currentStore.getSundayOpen());
            edSundayClose.setText(Store.currentStore.getSundayClose());
            edMondayClose.setText(Store.currentStore.getMondayClose());
            edMondayOpen.setText(Store.currentStore.getMondayOpen());
            edTuesdayOpen.setText(Store.currentStore.getTuesdayOpen());
            edTuesdayClose.setText(Store.currentStore.getTuesdayClose());
            edWednesdayClose.setText(Store.currentStore.getWednesdayClose());
            edWednesdayOpen.setText(Store.currentStore.getWednesdayOpen());
            edThursdayClose.setText(Store.currentStore.getThursdayClose());
            edThursdayOpen.setText(Store.currentStore.getThursdayOpen());
            edFridayClose.setText(Store.currentStore.getFridayClose());
            edFridayOpen.setText(Store.currentStore.getFridayOpen());
            //edManager.setText(Integer.toString(Store.currentStore.getManager()));
            edStoreName.setText(Store.currentStore.getName());
            edAddress.setText(Store.currentStore.getAddress());
        }

        managers = new ArrayList<>();
        if(ManagerProfile.managers.isEmpty()) {
            jsonH.makeJsonArryReq(Const.URL_JSON_ARRAY_ALL_USERS);
        }else{
            managers.addAll(ManagerProfile.managers);
            for(int i = 0; i<managers.size(); i++){
                if(managers.get(i).getId() == Store.currentStore.getManager()){
                    edManager.setText(managers.get(i).getName());
                    currentManager = managers.get(i);
                }
            }
        }

        edManager.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newManager = (ManagerProfile)parent.getItemAtPosition(position);
                edManager.setText(newManager.getName());
                edManager.clearFocus();
            }

        });

        managerListAdapter = new ManagerListAdapter(Create_Update_Store.this, android.R.layout.simple_expandable_list_item_1,managers);
        edManager.setAdapter(managerListAdapter);
        edManager.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }


    /**
     * Recieves all teh buttons clicks and determines what to do based off the button
     *
     * @param v View of the button that contatins the id
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnSaveStoreProf:

                if(areFieldsEmpty()) {

                    ArrayList<JSONVariable> bodyList = new ArrayList<>();
                    if(createUpdateUser.equals("Update")) {
                        bodyList.add(new JSONVariable("id", Integer.toString(Store.currentStore.getID())));
                    }
                    bodyList.add(new JSONVariable("name", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("address", edAddress.getText().toString().trim()));
                    if(newManager == null && currentManager == null) {
                        bodyList.add(new JSONVariable("manager", Integer.toString(Profile.currentLogin.getId())));
                    }else if(newManager== null){
                        bodyList.add(new JSONVariable("manager", Integer.toString(currentManager.getId())));
                    }else{
                        bodyList.add(new JSONVariable("manager", Integer.toString(newManager.getId())));
                    }
                    bodyList.add(new JSONVariable("latitude", Double.toString(pointLocation.getLatitude())));
                    bodyList.add(new JSONVariable("longitude",Double.toString(pointLocation.getLongitude())));
                    bodyList.add(new JSONVariable("opens_sunday", edSundayOpen.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_sunday", edSundayClose.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_monday", edMondayOpen.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_monday", edMondayClose.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_tuesday", edTuesdayOpen.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_tuesday", edThursdayClose.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_wednesday", edWednesdayOpen.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_wednesday", edWednesdayClose.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_thursday", edThursdayOpen.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_thursday", edThursdayClose.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_friday", edFridayOpen.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_friday", edFridayClose.getText().toString().trim()));
                    bodyList.add(new JSONVariable("opens_saturday", edSaturdayOpen.getText().toString().trim()));
                    bodyList.add(new JSONVariable("closes_saturday", edSaturdayClose.getText().toString().trim()));


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

    /**
     * Receives the response from the server in JSONObject form that contatins the new store object
     * with the update fields and of the new store object if applicable, and fills the fields on the
     * screen with the new values
     *
     * @param response JSONObject response holding a {@link Store} instance in JSON form
     */
    @Override
    public void onSuccess(JSONObject response) {

        Store.copyStore(Store.currentStore, Store.getStore(response));

        for(int i = 0; i<ManagerProfile.managers.size(); i++){
            if(ManagerProfile.managers.get(i).getId() == Store.currentStore.getManager()){
                currentManager = ManagerProfile.managers.get(i);
            }
        }

        edSaturdayClose.setText(Store.currentStore.getSaturdayClose());
        edSaturdayOpen.setText(Store.currentStore.getSaturdayOpen());
        edSundayOpen.setText(Store.currentStore.getSundayOpen());
        edSundayClose.setText(Store.currentStore.getSundayClose());
        edMondayClose.setText(Store.currentStore.getMondayClose());
        edMondayOpen.setText(Store.currentStore.getMondayOpen());
        edTuesdayOpen.setText(Store.currentStore.getTuesdayOpen());
        edTuesdayClose.setText(Store.currentStore.getTuesdayClose());
        edWednesdayClose.setText(Store.currentStore.getWednesdayClose());
        edWednesdayOpen.setText(Store.currentStore.getWednesdayOpen());
        edThursdayClose.setText(Store.currentStore.getThursdayClose());
        edThursdayOpen.setText(Store.currentStore.getThursdayOpen());
        edFridayClose.setText(Store.currentStore.getFridayClose());
        edFridayOpen.setText(Store.currentStore.getFridayOpen());
        if(currentManager != null) {
            edManager.setText(currentManager.getName());
        }
        edStoreName.setText(Store.currentStore.getName());
        edAddress.setText(Store.currentStore.getAddress());

        message.dismissMessage();
        finish();

    }

    /**
     * Receives the response from the Sever in from of JSON Array and takes this array and makes it into a list
     * of {@link ManagerProfile} instances
     *
     * @param response JSONArray response holding managers
     */
    @Override
    public void onSuccess(JSONArray response) {

        for(int i = 0; i<response.length();i++){

            try {
                ManagerProfile m = ManagerProfile.getProfileInfo(response.getJSONObject(i));
                if(m.getAccountType() == AccountType.MANAGER_ACCOUNT || m.getAccountType() == AccountType.ADMIN_ACCOUNT){
                    ManagerProfile.addManagerToList(m);
                    if(m.getId() == Store.currentStore.getManager()){
                        edManager.setText(m.getName());
                        currentManager = m;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        managers.clear();
        managers.addAll(ManagerProfile.managers);
        managerListAdapter.clear();
        managerListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccess(String response) {

    }

    /**
     * Error Handler of an error returned from the request
     *
     * @param error the error returned
     */
    @Override
    public void onError(VolleyError error) {

        message.dismissMessage();
        message.showMessage("Error: " + error.toString(),1);

    }

    /**
     * Checks to see if the fields hold good data to be submitted to the server
     *
     * @return boolean returns true if the fields are good, false if there is an issue
     */
    private boolean areFieldsEmpty(){

        boolean check = true;

        if(edAddress.getText().toString().trim().isEmpty()){
//            String text = "empty";
//            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            check = false;
            edAddress.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            pointLocation = Store.getLocationFromAddress(edAddress.getText().toString().trim(), Create_Update_Store.this);
            if(pointLocation == null){
//                String text = "null";
//                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                check = false;
                edAddress.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }else{
//                String text = "fine";
//                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                edAddress.setTextColor(getResources().getColor(R.color.colorTextSecond));
            }
        }
        if(edManager.getText().toString().trim().isEmpty() || (!edManager.getText().toString().trim().equals(Profile.currentLogin.getName()) && newManager == null && currentManager != null &&!edManager.getText().toString().trim().equals(currentManager.getName()))){
            check = false;
            edManager.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else if(newManager != null && !newManager.getName().equals(edManager.getText().toString().trim())){
            check = false;
            edManager.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        else {
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
            edMondayClose.setTextColor(getResources().getColor(R.color.colorTextSecond));
        }
        if(edMondayOpen.getText().toString().trim().isEmpty()){
            check = false;
            edMondayOpen.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edMondayOpen.setTextColor(getResources().getColor(R.color.colorTextSecond));
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



}