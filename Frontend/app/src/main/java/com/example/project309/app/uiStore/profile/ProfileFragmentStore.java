package com.example.project309.app.uiStore.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.VolleyError;
import com.example.project309.R;
import com.example.project309.app.AppController;
import com.example.project309.app.Create_Update_Store;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.JSONVariable;
import com.example.project309.app.MessageBoxInter;
import com.example.project309.app.PointLocation;
import com.example.project309.app.Profile;
import com.example.project309.app.RequestMethod;
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileFragmentStore extends Fragment implements ViewListenerInter, View.OnClickListener {

    private ProfileViewModelStore profileViewModelStore;
    private Store storeM;

    EditText edStoreName, edAddress, edManager, edSundayOpen, edSundayClose, edMondayOpen, edMondayClose,
            edTuesdayOpen, edTuesdayClose, edWednesdayOpen, edWednesdayClose, edThursdayOpen, edThursdayClose,
            edFridayOpen, edFridayClose, edSaturdayOpen, edSaturdayClose;

    TextView txtStoreName, txtAddress, txtManager, txtSundayOpen, txtSundayClose, txtMondayOpen, txtMondayClose,
            txtTuesdayOpen, txtTuesdayClose, txtWednesdayOpen, txtWednesdayClose, txtThursdayOpen, txtThursdayClose,
            txtFridayOpen, txtFridayClose, txtSaturdayOpen, txtSaturdayClose;

    Button btnSave;

    JSONHandlerInter jsonH;

    MessageBoxInter message;

    PointLocation pointLocation;

    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModelStore =
                ViewModelProviders.of(this).get(ProfileViewModelStore.class);
        View root = inflater.inflate(R.layout.fragment_profile_store, container, false);
        final TextView textView = root.findViewById(R.id.text_profile_store);
        profileViewModelStore.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        profileViewModelStore.getStore().observe(this, new Observer<Store>() {
            @Override
            public void onChanged(Store store) {
                storeM = store;
            }
        });

        context = root.getContext();

        message = AppController.getInstance().getMessageBoxBuilderInstance();
        message.setContext(context);

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

        btnSave =root.findViewById(R.id.btnSaveStoreProf);
        btnSave.setOnClickListener(this);

        edStoreName =root.findViewById(R.id.editStoreName);
        edAddress =root.findViewById(R.id.editAddress);
        edManager =root.findViewById(R.id.editManager);
        edSundayOpen =root.findViewById(R.id.editSundayOpen);
        edSundayClose =root.findViewById(R.id.editSundayClose);
        edMondayOpen =root.findViewById(R.id.editMondayOpen);
        edMondayClose =root.findViewById(R.id.editMondayClose);
        edTuesdayOpen =root.findViewById(R.id.editTuesdayOpen);
        edTuesdayClose =root.findViewById(R.id.editTuesdayClose);
        edWednesdayOpen =root.findViewById(R.id.editWednesdayOpen);
        edWednesdayClose =root.findViewById(R.id.editWednesdayClose);
        edThursdayOpen =root.findViewById(R.id.editThursdayOpen);
        edThursdayClose =root.findViewById(R.id.editThursdayClose);
        edFridayOpen =root.findViewById(R.id.editFridayOpen);
        edFridayClose =root.findViewById(R.id.editFridayClose);
        edSaturdayOpen =root.findViewById(R.id.editSaturdayOpen);
        edSaturdayClose =root.findViewById(R.id.editSaturdayClose);

        txtStoreName =root.findViewById(R.id.txtStoreName);
        txtAddress =root.findViewById(R.id.txtAddress);
        txtManager =root.findViewById(R.id.txtManager);
        txtSundayOpen =root.findViewById(R.id.txtSundayOpen);
        txtSundayClose =root.findViewById(R.id.txtSundayClose);
        txtMondayOpen =root.findViewById(R.id.txtMondayOpen);
        txtMondayClose =root.findViewById(R.id.txtMondayClose);
        txtTuesdayOpen =root.findViewById(R.id.txtTuesdayOpen);
        txtTuesdayClose =root.findViewById(R.id.txtTuesdayClose);
        txtWednesdayOpen =root.findViewById(R.id.txtWednesdayOpen);
        txtWednesdayClose =root.findViewById(R.id.txtWednesdayClose);
        txtThursdayOpen =root.findViewById(R.id.txtThursdayOpen);
        txtThursdayClose =root.findViewById(R.id.txtThursdayClose);
        txtFridayOpen =root.findViewById(R.id.txtFridayOpen);
        txtFridayClose =root.findViewById(R.id.txtFridayClose);
        txtSaturdayOpen =root.findViewById(R.id.txtSaturdayOpen);
        txtSaturdayClose =root.findViewById(R.id.txtSaturdayClose);

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
        edManager.setText(Profile.currentLogin.getName());
        edStoreName.setText(Store.currentStore.getName());
        edAddress.setText(Store.currentStore.getAddress());
        
        return root;
    }

    @Override
    public void onSuccess(JSONObject response) {

        Store.currentStore = Store.getStore(response);
        message.dismissMessage();

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
        edManager.setText(Profile.currentLogin.getName());
        edStoreName.setText(Store.currentStore.getName());
        edAddress.setText(Store.currentStore.getAddress());

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

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btnSaveStoreProf:

                if(!areFieldsEmpty()) {

                    ArrayList<JSONVariable> bodyList = new ArrayList<>();
                    bodyList.add(new JSONVariable("id", Integer.toString(Store.currentStore.getID())));
                    bodyList.add(new JSONVariable("name", edStoreName.getText().toString().trim()));
                    bodyList.add(new JSONVariable("address", edAddress.getText().toString().trim()));
                    bodyList.add(new JSONVariable("manager", Integer.toString(Profile.currentLogin.getId())));
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

                    jsonH.makeJsonObjReqBody(Const.URL_JSON_UPDATE_STORE, bodyList, RequestMethod.POST);
                    message.showMessage("Updating...", 3);


                }else{
                    message.showMessage("Fill in the required fields", 1);
                }


                break;

        }

    }

    private boolean areFieldsEmpty(){

        boolean check = true;

        if(edAddress.getText().toString().trim().isEmpty()){
            check = false;
            edAddress.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }else{
            edAddress.setTextColor(getResources().getColor(R.color.colorTextSecond));
            pointLocation = Store.getLocationFromAddress(edAddress.getText().toString().trim(), context);
            if(pointLocation == null){
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

        return !check;

    }
}