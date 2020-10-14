package com.example.project309.app;

import android.util.Log;

public interface MessageBoxListenerInter {

    public void onDismiss(String message);
    public void neutralButtonPressed(String message);
    public void positiveButtonPressed(String message);
    public void negativeButtonPressed(String message);

}
