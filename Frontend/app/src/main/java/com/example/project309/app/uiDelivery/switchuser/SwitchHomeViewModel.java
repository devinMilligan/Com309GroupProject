package com.example.project309.app.uiDelivery.switchuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * This class holds the current describing text for the fragment and any other varaibles that can be updtated
 *
 * @author Devin Milligan
 */
public class SwitchHomeViewModel extends ViewModel {

    /**
     * Holds the current describing text for the fragment
     */
    private MutableLiveData<String> mText;

    /**
     * Default constructor used to set the current describin text
     */
    public SwitchHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is switch home fragment");
    }

    /**
     * Returns the current describing text for the fragment
     *
     * @return LiveData instance that holds the text
     */
    public LiveData<String> getText() {
        return mText;
    }
}