package com.example.project309.app.uiAdmin.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * This class holds the describing text of the fragment
 *
 * @author Devin Milligan
 */
public class LogOutViewModelAdmin extends ViewModel {

    /**
     * Holds the live describing text that describes the fragment
     */
    private MutableLiveData<String> mText;

    /**
     * Default constructor that sets the describing text
     */
    public LogOutViewModelAdmin() {
        mText = new MutableLiveData<>();
        mText.setValue("This is log out fragment");
    }

    /**
     * Returns the current describing text of the fragment
     *
     * @return LiveData instance holding the describing text
     */
    public LiveData<String> getText() {
        return mText;
    }
}