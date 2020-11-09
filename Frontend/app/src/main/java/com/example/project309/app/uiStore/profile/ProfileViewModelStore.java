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

/**
 * This class gets the managers and the store information from the currentStore and the server
 *
 * @author Devin Milligan
 */
public class ProfileViewModelStore extends ViewModel implements ViewListenerInter {

    /**
     * Contatins the describing text for the fragment
     */
    private MutableLiveData<String> mText;
    /**
     * Contains the current {@link Store} instance
     */
    private MutableLiveData<Store> store;
    /**
     * Contains the current list of all managers as {@link ManagerProfile}
     */
    private MutableLiveData<ArrayList<ManagerProfile>> managers;
    /**
     * {@link com.example.project309.app.JSONHandler} instance that is used to make requests to the server
     */
    private JSONHandlerInter jsonH;
    /**
     * {@link ProfileFragmentStore} instance used to update the current Manager list on the display
     */
    private ProfileFragmentStore storeFrag;

    /**
     * Default Constructor used to set the describing text and intitialize {@link com.example.project309.app.JSONHandler}
     */
    public ProfileViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("Store Settings");

        jsonH = AppController.getInstance().getJSONHandlerInstance();
        jsonH.setListener(this);

    }

    /**
     * Sets the current fragment that is to be updated if the manager list updates
     *
     * @param sF ProfileFragmentStore instance that will be updated
     */
    public void setFragment(ProfileFragmentStore sF){
        storeFrag = sF;
    }

    /**
     * Returns the current describing text for the fragment
     *
     * @return LiveData instance containing the text
     */
    public LiveData<String> getText() {
        return mText;
    }

    /**
     * Returns the current {@link Store} instance that is currently being edited
     *
     * @return LiveData Instance that contains the {@link Store} instance
     */
    public LiveData<Store> getStore(){

        store = new MutableLiveData<>();

        store.setValue(Store.currentStore);

        return store;

    }

    /**
     * Determines if a list of managers has already been collected and if not it calls to get one from the server
     *
     * @return LIveData instance that contains a list of {@link ManagerProfile}s
     */
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

    /**
     * Makes a request to the server for the list of all the managers
     */
    private void loadManagers(){
        jsonH.makeJsonArryReq(Const.URL_JSON_ARRAY_ALL_USERS);
    }

    /**
     * Receives the response from the server for the JSONObjecst and gets an {@link ManagerProfile} from the
     * json format and add it to the list of managers
     *
     * @param response
     */
    @Override
    public void onSuccess(JSONObject response) {
        ManagerProfile m = ManagerProfile.getProfileInfo(response);
        if(m.getAccountType() == AccountType.MANAGER_ACCOUNT || m.getAccountType() == AccountType.ADMIN_ACCOUNT){
            ManagerProfile.addManagerToList(m);
        }
    }

    /**
     * Recieves the JSONArray of users from the server and gets a list of managers from it and
     * sets the current list to this new list of managers
     *
     * @param response JSONArray of {@link ManagerProfile}s in json form
     */
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

    /**
     * Handles the error given from a request from the server
     *
     * @param error the error returned from the request
     */
    @Override
    public void onError(VolleyError error) {
        managers.setValue(null);
        Log.d("PROFILESTORE", error.toString());
    }
}