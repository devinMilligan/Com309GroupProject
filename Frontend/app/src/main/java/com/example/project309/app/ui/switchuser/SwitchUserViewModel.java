package com.example.project309.app.ui.switchuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Holds the value of the describing text for the fragment
 *
 * @author Devin Milligan
 */
public class SwitchUserViewModel extends ViewModel {

    /**
     * Holds the live value of the describing text
     */
    private MutableLiveData<String> mText;

    /**
     * Default constructor that sets the describing text
     */
    public SwitchUserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is switch user fragment");
    }

    /**
     * Returns the current describing text for the fragment
     *
     * @return LiveData<String> with the text contained
     */
    public LiveData<String> getText() {
        return mText;
    }
}