package com.example.project309.app.uiStore.logout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * This class holds the current describing text of the fragment and any other values that can changed
 *
 * @author Devin Milligan
 */
public class LogOutViewModelStore extends ViewModel {

    /**
     * This holds the current describing text value of the fragment
     */
    private MutableLiveData<String> mText;

    /**
     * Default Constructor that sets the describing text of the fragment
     */
    public LogOutViewModelStore() {
        mText = new MutableLiveData<>();
        mText.setValue("This is log out fragment");
    }

    /**
     * Returns the describing text for the fragment
     *
     * @return LiveData instance that holds the text
     */
    public LiveData<String> getText() {
        return mText;
    }
}