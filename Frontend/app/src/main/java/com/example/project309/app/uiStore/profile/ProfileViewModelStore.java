package com.example.project309.app.uiStore.profile;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.project309.app.AccountType;
import com.example.project309.app.AppController;
import com.example.project309.app.JSONHandlerInter;
import com.example.project309.app.ManagerProfile;
import com.example.project309.app.Profile;
import com.example.project309.app.Store;
import com.example.project309.app.ViewListenerInter;
import com.example.project309.net_utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileViewModelStore extends ViewModel implements ViewListenerInter {

    private MutableLiveData<String> mText;
    private MutableLiveData<Store> store;
    private MutableLiveData<ArrayList<ManagerProfile>> managers;
    private JSONHandlerInter jsonH;
    private ProfileFragmentStore storeFrag;

    public ProfileViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Store Settings");

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

    }

    public void setFragment(ProfileFragmentStore sF){
        storeFrag = sF;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Store> getStore(){

        store = new MutableLiveData<>();

        store.setValue(Store.currentStore);

        return store;

    }

    public LiveData<ArrayList<ManagerProfile>> getManagers(){
        if(managers == null){

            managers = new MutableLiveData<>();

            if(ManagerProfile.managers.isEmpty()) {
                loadManagers();
            }else{
                managers.setValue(ManagerProfile.managers);
            }

        }
        return managers;
    }

    private void loadManagers(){
        jsonH.makeJsonArryReq(Const.URL_JSON_ARRAY_ALL_USERS);
    }

    @Override
    public void onSuccess(JSONObject response) {
        ManagerProfile m = ManagerProfile.getProfileInfo(response);
        if(m.getAccountType() == AccountType.MANAGER_ACCOUNT || m.getAccountType() == AccountType.ADMIN_ACCOUNT){
            ManagerProfile.addManagerToList(m);
        }
    }

    @Override
    public void onSuccess(JSONArray response) {
        for(int i = 0; i<response.length();i++){

            try {
                onSuccess(response.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        managers.setValue(ManagerProfile.managers);
        storeFrag.updateList();
    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {
        managers.setValue(null);
        Log.d("PROFILESTORE", error.toString());
    }
}