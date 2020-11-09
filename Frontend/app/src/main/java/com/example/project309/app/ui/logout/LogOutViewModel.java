package com.example.project309.app.ui.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * This class holds and allows getting of the live values for the LogOut Fragment
 *
 * @author Devin Milligan
 */
public class LogOutViewModel extends ViewModel {

    /**
     * Stores the live description text of the fragment
     */
    private MutableLiveData<String> mText;

    /**
     * Default constructor that sets the live text
     */
    public LogOutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    /**
     * Returns the current live text value for the fragment
     *
     * @return LiveData<String> holds the live text value
     */
    public LiveData<String> getText() {
        return mText;
    }
}